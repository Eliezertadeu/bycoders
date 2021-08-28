import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { EnumRouter } from '../api/enum-router'
import { TransactionDTO } from '../../dto/transaction-dto';
import { TransactionResponseDTO } from '../../dto/transaction-response-dto';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {


  constructor(private http: HttpClient) { }

  getTransactions(): Observable<TransactionDTO[]> {
    return this.http.get<TransactionDTO[]>(EnumRouter.Transctions)
  }

  getTransactionsByStore(): Observable<TransactionResponseDTO[]> {
    return this.http.get<TransactionResponseDTO[]>(EnumRouter.TransctionsStore)
  }

  postUploadTransactionFile(form: FormData) {
    this.http.post(EnumRouter.TransctionsFileUpload, form)
      .toPromise()
      .then(res => alert('Dados carregados'))
      .catch(err => {
        alert('Não foi possível carregar seu arquivo');
      });
  }
}
