package com.kim344.cleanarchitecturesample

import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kim344.cleanarchitecturesample.utils.EndlessRecyclerViewScrollListener
import com.kim344.cleanarchitecturesample.views.search.MovieAdapter
import com.kim344.cleanarchitecturesample.views.search.MovieSearchViewModel
import com.kim344.domain.search.Movie

@BindingAdapter("htmlText")
fun TextView.setHtmlText(html: String) {
    text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("urlImage")
fun ImageView.setUrlImage(url: String) {
    Glide.with(this).load(url)
        .placeholder(R.drawable.ic_default)
        .into(this)
}

@BindingAdapter("movieRating")
fun RatingBar.setMovieRating(score: String) {
    rating = (score.toFloatOrNull() ?: 0f) / 2
}

@BindingAdapter("setItems")
fun RecyclerView.setAdapterItems(items: MutableList<Movie>?) {
    items?.let {
        (adapter as MovieAdapter).submitList(it.toMutableList())
    }
}

@BindingAdapter("endlessScroll")
fun RecyclerView.setEndlessScroll(
    viewModel: MovieSearchViewModel
) {
    val scrollListener =
        object : EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestPagingMovie(totalItemsCount + 1)
            }

        }

    addOnScrollListener(scrollListener)
}