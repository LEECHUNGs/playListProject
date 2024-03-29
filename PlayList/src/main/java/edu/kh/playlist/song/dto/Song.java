package edu.kh.playlist.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Song {
	private int songNo;
	private String songTitle;
	private String songArtist;
	private String songAlbum;
	private int songLike;
}
