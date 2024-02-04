package com.AuthRegLog.Login.MapStruct.Mapper;

import org.mapstruct.Mapper;

import com.AuthRegLog.Login.MapStruct.Dto.BlogFindDto;
import com.AuthRegLog.Login.MapStruct.Dto.BlogSaveDto;
import com.AuthRegLog.Login.model.Blog;

@Mapper(componentModel = "spring")
public interface BlogStructMapper {
	Blog blogSaveDtoToBlog(BlogSaveDto blogSaveDto);

	BlogSaveDto blogToBlogSaveDto(Blog blog);

	Blog blogFindDtoToBlog(BlogFindDto blogFindDto);

//	List<Blog> blogFindAllDtoToAllBlogs(BlogAllDto blogFindAllDto);
}
