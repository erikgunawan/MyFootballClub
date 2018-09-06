package id.ergun.myfootballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    val club_list: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI(club_list).setContentView(this)

        initData()
    }

    class MainActivityUI(var club_list : MutableList<Club>) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

            verticalLayout {
                recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = ClubAdapter(context, club_list) {
                        toast("${it.name} dipilih")
                        startActivity<ClubDetailActivity>("club" to it)
                    }
                }
            }
        }
    }

    private fun initData() {
        val club_name_arr = resources.getStringArray(R.array.club_name_arr)
        val club_image_arr = resources.obtainTypedArray(R.array.club_image_arr)
        val club_description_arr = resources.getStringArray(R.array.club_description_arr)

        club_list.clear()

        for (i in club_name_arr.indices) {
            club_list.add(Club(club_name_arr[i], club_image_arr.getResourceId(i, 0), club_description_arr[i]))
        }

        club_image_arr.recycle()
    }
}