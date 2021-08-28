import { Component, Input, OnInit } from '@angular/core';
import { TransactionDTO } from 'src/app/dto/transaction-dto';

@Component({
  selector: 'app-base-table',
  templateUrl: './base-table.component.html',
  styleUrls: ['./base-table.component.css']
})
export class BaseTableComponent {

  constructor() { }

  @Input()
  transactions: TransactionDTO[] = []


}
