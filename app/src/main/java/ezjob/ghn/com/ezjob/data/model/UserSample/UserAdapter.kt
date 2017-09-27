package ezjob.ghn.com.ezjob.data.model.UserSample

import android.view.View
import android.widget.TextView
import ezjob.ghn.com.ezjob.R
import ezjob.ghn.com.ezjob.base.BaseAdapter
import ezjob.ghn.com.ezjob.base.BaseViewHolder
import ezjob.ghn.com.ezjob.data.sample.User

/**
 * Created by Van T Tran on 08-Aug-17.
 */
class UserAdapter: BaseAdapter<User, UserAdapter.UserViewHolder>(){
    override fun getItemViewId() = R.layout.view_item

    override fun instantiateViewHolder(view: View?) = UserViewHolder(view)


    class UserViewHolder(itemView : View?) : BaseViewHolder<User>(itemView){
        val tvName by lazy { itemView?.findViewById<TextView>(R.id.tvName) }
        val tvDes by lazy { itemView?.findViewById<TextView>(R.id.tvDescription) }
        override fun onBind(item: User) {
            tvName?.text = item.login
            tvDes?.text = item.url
        }

    }
}