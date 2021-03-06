package id.ergun.myfootballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private val clubList: MutableList<Club> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI(clubList).setContentView(this)

        initData()
    }

    class MainActivityUI(private var club_list : MutableList<Club>) : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

            verticalLayout {
                recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                    adapter = ClubAdapter(club_list) {
                        toast("${it.name} dipilih")
                        startActivity<ClubDetailActivity>("club" to it)
                    }
                }
            }
        }
    }

    private fun initData() {
        val clubNameArr = resources.getStringArray(R.array.club_name_arr)
        val clubImageArr = resources.obtainTypedArray(R.array.club_image_arr)
        val clubDescriptionArr = resources.getStringArray(R.array.club_description_arr)

        clubList.clear()

        for (i in clubNameArr.indices) {
            clubList.add(Club(clubNameArr[i], clubImageArr.getResourceId(i, 0), clubDescriptionArr[i]))
        }

        clubImageArr.recycle()
    }
}