package com.AuthRegLog.Login.mapper;

import org.mapstruct.Mapper;

import com.AuthRegLog.Login.dto.BlogFindDto;
import com.AuthRegLog.Login.dto.BlogSaveDto;
import com.AuthRegLog.Login.model.Blog;

@Mapper(componentModel = "spring")
public interface BlogStructMapper {
	Blog blogSaveDtoToBlog(BlogSaveDto blogSaveDto);

	BlogSaveDto blogToBlogSaveDto(Blog blog);

	Blog blogFindDtoToBlog(BlogFindDto blogFindDto);

//	List<Blog> blogFindAllDtoToAllBlogs(BlogAllDto blogFindAllDto);
}
