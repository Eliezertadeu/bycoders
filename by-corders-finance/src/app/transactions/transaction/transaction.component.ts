import { Component, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/app/dto/transaction-dto';
import { TransactionResponseDTO } from 'src/app/dto/transaction-response-dto';
import { TransactionService } from '../services/transaction.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  transactions: TransactionDTO[] = [];
  transactionsByStore: TransactionResponseDTO[] = []
  view: Number = 1

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getAllTransactions()
    this.getAllByStore()
  }

  getAllTransactions() {
    this.transactionService.getTransactions()
      .subscribe((trans: TransactionDTO[]) => { this.transactions = trans; })
  }

  getAllByStore() {
    this.transactionService.getTransactionsByStore().subscribe((trans: TransactionResponseDTO[]) => {
      this.transactionsByStore = trans;
    });
  }

  inputFileChange(event: any) {
    if (event.target.files && event.target.files[0]) {

      const file = event.target.files[0];
      const formData = new FormData();

      formData.append('file', file);

      this.transactionService.postUploadTransactionFile(formData)
      this.changeView(1);
    }
  }

  changeView(view: Number) {
    this.getAllTransactions()
    this.getAllByStore()
    this.view = view
  }
}
