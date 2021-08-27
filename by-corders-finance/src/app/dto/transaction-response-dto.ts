import { Owner } from "./owner-dto";
import { TransactionDTO } from "./transaction-dto";

export interface TransactionResponseDTO {
    id: Number,
    storeName: String,
    owner: Owner,
    total: BigInteger,
    inputs: Number,
    outputs: Number,
    transactions: TransactionDTO[]
}