import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionComponent } from './transaction/transaction.component';
import { BaseTableComponent } from '../shared/base-table/base-table.component';



@NgModule({
  declarations: [
    TransactionComponent,
    BaseTableComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [TransactionComponent]
})
export class TransactionsModule { }
