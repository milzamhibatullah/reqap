package id.milzamhb.finance.reqap.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.Nullable
import java.util.*

@Entity(tableName = "transaction_table")
data class Transaction(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int,
                       @ColumnInfo(name = "name") val name : String,
                       @ColumnInfo(name = "category") val category : String,
                       @ColumnInfo(name = "type_category") val type_category : Int,
                       @ColumnInfo(name = "date") val date: String,
                       @ColumnInfo(name = "amount") val amount : Double,
                       @ColumnInfo(name = "payment_method")val payment : String)