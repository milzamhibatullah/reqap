package id.milzamhb.finance.reqap.utils
import id.milzamhb.finance.reqap.R
data class CategoryItem(val title : String,
                        val image : Int)
class AddItemCategory{
    companion object {
        fun expenseItem() : List<CategoryItem>{
            return listOf(
                CategoryItem(title = "Makanan",image = R.drawable.ic_restaurant_menu_white_24dp ),
                CategoryItem(title = "Transportasi",image = R.drawable.ic_directions_bus_white_24dp),
                CategoryItem(title = "Rumah Tangga",image = R.drawable.ic_home_white_24dp),
                CategoryItem(title = "Pakaian",image = R.drawable.ic_local_mall_white_24dp),
                CategoryItem(title = "Pulsa/Data",image = R.drawable.ic_phone_android_white_24dp),
                CategoryItem(title = "Pendidikan",image = R.drawable.ic_school_white_24dp),
                CategoryItem(title = "Kesahatan",image = R.drawable.ic_favorite_white_24dp),
                CategoryItem(title = "Liburan",image = R.drawable.ic_beach_access_white_24dp),
                CategoryItem(title = "Hiburan",image = R.drawable.ic_local_movies_white_24dp),
                CategoryItem(title = "Lainnya",image = R.drawable.ic_add_white)
            )
        }

        fun incomeItem():List<CategoryItem>{
            return listOf(
                CategoryItem(title = "Gaji",image = R.drawable.ic_attach_money_black_24dp),
                CategoryItem(title = "Tunjangan",image = R.drawable.ic_allowance),
                CategoryItem(title = "Bonus",image = R.drawable.ic_bonus),
                CategoryItem(title = "Lainnya",image = R.drawable.ic_add_white)
            )
        }
    }
}