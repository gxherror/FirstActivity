package top.xherror.first_activity

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.typeOf

class TitleLayout(context:Context,attr:AttributeSet) :LinearLayout(context,attr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
        val buttonBack:Button=findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            val activity=context as Activity
            activity.finish()
        }
        val buttonNext:Button=findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener {
            Toast.makeText(context,"unfinished",Toast.LENGTH_SHORT).show()
        }
    }
}

class Fruit(val name:String,val imageId :Int)

class FruitAdapterDeprecated(activity: Activity,val resourceId:Int,data:List<Fruit>):ArrayAdapter<Fruit>(activity,resourceId,data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val fruitImage:ImageView
        val fruitName:TextView
        if (convertView==null){
            view=LayoutInflater.from(context).inflate(resourceId,parent,false)
            fruitImage=view.findViewById(R.id.fruitImage)
            fruitName=view.findViewById(R.id.fruitName)
            view.tag= listOf(fruitImage,fruitName)
            val cache=view.tag as List<*>

        }else{
            view=convertView
            val cache=view.tag as List<*>
            fruitImage=cache[0] as ImageView
            fruitName=cache[1] as TextView
        }
        val fruit= getItem(position)
        if (fruit!=null){
            fruitImage.setImageResource(fruit.imageId)
            fruitName.text=fruit.name
        }
        return view
    }
}


class FruitAdapter(val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val viewHolder = ViewHolder(view)
        //
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked view ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitImage.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            Toast.makeText(parent.context, "you clicked image ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount() = fruitList.size
}
