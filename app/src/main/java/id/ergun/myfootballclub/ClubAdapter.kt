package id.ergun.myfootballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class ClubAdapter (private val context : Context, private val club_list : List<Club>,
                   private val listener: (Club) -> Unit)
    : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ClubUI().createView(AnkoContext.create(parent.context, parent)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(club_list[position], listener)
    }

    override fun getItemCount(): Int = club_list.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        lateinit var tvName: TextView
        lateinit var ivLogo : ImageView

        fun bindItem(club_list: Club, listener: (Club) -> Unit) {

            tvName = itemView.findViewById(R.id.tv_name)
            ivLogo = itemView.findViewById(R.id.iv_logo)

            tvName.text = club_list.name
            Glide.with(itemView.context).load(club_list.image).into(ivLogo)
            itemView.setOnClickListener {
                listener(club_list)
            }
        }
    }

    class ClubUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                padding = dip(16)
                lparams (width = matchParent, height = wrapContent)

                imageView(R.drawable.img_barca) {
                    id = R.id.iv_logo
                }.lparams(width = dip(50), height = dip(50))

                textView {
                    id = R.id.tv_name
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(width = wrapContent, height = wrapContent) { margin = dip(10) }
            }
        }
    }
}