import { TransactionTypeDTO } from "./transaction-type-dto";

export interface TransactionDTO {
    id: Number,
    date: Date,
    cpf: String,
    creaditCard: String,
    hour: String,
    type: TransactionTypeDTO
}
