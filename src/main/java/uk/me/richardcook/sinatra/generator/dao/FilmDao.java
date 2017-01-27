package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Film;
import uk.me.richardcook.sinatra.generator.model.FilmJoined;

import java.util.List;

public interface FilmDao {

	List<Film> findAll();

	Film find( int id );

	void save( Film film );

	void update( Film film );

	List<Film> search( String query );

	FilmJoined findJoined( int id );
}
