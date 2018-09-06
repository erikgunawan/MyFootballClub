package id.ergun.myfootballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.bumptech.glide.Glide
import org.jetbrains.anko.*


class ClubDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val club = intent.getParcelableExtra<Club>("club")
        ClubDetailUI(club).setContentView(this)
    }

    class ClubDetailUI(var club : Club) : AnkoComponent<ClubDetailActivity> {
        override fun createView(ui: AnkoContext<ClubDetailActivity>) = with(ui) {

            verticalLayout {
                padding = dip(16)

                imageView {
                    this@verticalLayout.gravity = Gravity.CENTER_HORIZONTAL
                    Glide.with(this).load(club.image).into(this)
                }.lparams(width = dip(100), height = dip(100))

                textView {
                    gravity = Gravity.CENTER_HORIZONTAL
                    textSize = sp(6).toFloat()
                    text = club.name
                }.lparams(width = wrapContent, height = wrapContent) { margin = dip(10) }

                textView {
                    gravity = Gravity.CENTER_HORIZONTAL
                    textSize = sp(4).toFloat()
                    text = club.description
                }.lparams(width = wrapContent, height = wrapContent) { margin = dip(10) }
            }
        }
    }
}