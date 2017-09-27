package ezjob.ghn.com.ezjob.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Van T Tran on 08-Aug-17.
 */
abstract class BaseViewHolder<D>(itemView : View?) : RecyclerView.ViewHolder(itemView){
    abstract fun onBind(item : D)
}