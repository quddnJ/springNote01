package com.bit.springNote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class NoteDto {

	private int id;
	private String writer;
	private String content;
}
