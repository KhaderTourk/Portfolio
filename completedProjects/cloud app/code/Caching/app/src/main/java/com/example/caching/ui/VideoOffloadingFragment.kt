package com.example.caching.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caching.MainActivity
import com.example.caching.databinding.FragmentVideoOffloadingBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


class VideoOffloadingFragment : Fragment() {

    private var player:SimpleExoPlayer?=null
    private var playWhenReady = true
    private var currentWindow:Int = 0
    private var playbackPosition:Long = 0

    private var _binding : FragmentVideoOffloadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVideoOffloadingBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun initPlayer(){
        player = SimpleExoPlayer.Builder(requireContext()).build()
        binding.videoView.player = player

        val mediaItem = MediaItem.fromUri(MainActivity.video)
        player!!.setMediaItem(mediaItem)

        player!!.prepare()
        player!!.playWhenReady = playWhenReady
        player!!.seekTo(currentWindow, playbackPosition)
    }

    private fun releasePlayer(){
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
        }
    }

    override fun onStart() {
        super.onStart()
        initPlayer()
    }

    override fun onPause() {
        releasePlayer()
        super.onPause()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
    }
}
