package com.learn.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.app.dto.MakeFavDto;
import com.learn.app.service.FavouriteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fav")
public class FavouriteController {
	@Autowired
	FavouriteService favService;

	@PostMapping("/make")
	public ResponseEntity<?> postMakeFav(@RequestHeader(name = "Authorization") String header,
			@Valid @RequestBody MakeFavDto makeFavDto) {
		System.out.println(header);
		return favService.makeFavourite(header, makeFavDto);
	}

	@PostMapping("/remove")
	public ResponseEntity<?> postRemoveFav(@RequestHeader(name = "Authorization") String header,
			@Valid @RequestBody MakeFavDto makeUnFavDto) {
		return favService.makeUnFavourite(header, makeUnFavDto);
	}

	@PostMapping("/all")
	public ResponseEntity<?> postAllFav(@RequestHeader(name = "Authorization") String header) {
		return favService.getAllFav(header);
	}
}
