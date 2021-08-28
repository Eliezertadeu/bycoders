import { TransactionTypeDTO } from "./transaction-type-dto";

export interface TransactionDTO {
    id: Number,
    date: Date,
    cpf: String,
    creditCard: String,
    hour: String,
    value: BigInteger,
    type: TransactionTypeDTO
}
