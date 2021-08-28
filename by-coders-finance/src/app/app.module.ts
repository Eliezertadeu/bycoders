import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { TransactionsModule } from './transactions/transactions.module';
import { TransactionService } from './transactions/services/transaction.service';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    TransactionsModule
  ],
  providers: [TransactionService],
  bootstrap: [AppComponent],
})
export class AppModule { }
