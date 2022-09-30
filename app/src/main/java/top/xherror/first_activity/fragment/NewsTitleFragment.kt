package top.xherror.first_activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.xherror.first_activity.fragment.NewsContentFragment
import kotlin.math.log


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class News(val newsTitle: String,val newsContent:String)
/**
 * A simple [Fragment] subclass.
 * Use the [NewsTitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsTitleFragment : Fragment(){
    private val newsList=ArrayList<News>()
    private val name="NewsTitleFragment"
    //requireView()
    //requireActivity()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var isTwoPane=false
    private var isBackground=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event.targetState == Lifecycle.State.CREATED) {
                    isTwoPane= requireActivity().findViewById<View>(R.id.fragmentBackground)!=null
                    val newsTitleRecyclerView:RecyclerView=requireView().findViewById(R.id.newsTitleRecyclerView)
                    val layoutManager= LinearLayoutManager(requireActivity())
                    newsTitleRecyclerView.layoutManager=layoutManager
                    newsTitleRecyclerView.adapter=NewsAdapter(newsList)
                    lifecycle.removeObserver(this)
                }
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        newsList.add(News("Shocking!","faafbyucfthggerjrgjglkjgljrgjargljkrgljkrgajkargjargljk;rwuybgcfrg"))
        newsList.add(News("Astonishing!","vsrgsrskskhbjsdgbhjksghnjkshnkshngslhnkgshnjksnjkg"))
        return inflater.inflate(R.layout.fragment_news_title, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsTitleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsTitleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class NewsAdapter(private val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        private val name="NewsAdapter"
        val fragment= NewsContentFragment()
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val viewHolder = ViewHolder(view)
            viewHolder.itemView.setOnClickListener {
                val position = viewHolder.adapterPosition
                val news=newsList[position]
                when(isTwoPane){
                    true-> {
                        if (isBackground){
                            replaceFragment(fragment)
                            isBackground=false
                        }
                        val fragmentNewsContentNewsTitle: TextView =
                            fragment.requireView().findViewById(R.id.fragmentNewsContentNewsTitle)
                        val fragmentNewsContentNewsContent: TextView =
                            fragment.requireView().findViewById(R.id.fragmentNewsContentNewsContent)
                        fragmentNewsContentNewsTitle.text = news.newsTitle
                        fragmentNewsContentNewsContent.text = news.newsContent
                    }
                    false-> {
                        NewsContentActivity.actionStart(parent.context, newsTitle =news.newsTitle , newsContent =news.newsContent )
                    }
                }
            }
            return viewHolder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.newsTitle
        }

        override fun getItemCount() = newsList.size
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager=requireActivity().supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentBackground,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        fragmentManager.executePendingTransactions()
    }


}


