package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.ForOtherDao;
import uk.me.richardcook.sinatra.generator.model.ForOther;

import java.util.List;


@Service
public class ForOtherService {

	@Autowired
	private ForOtherDao forOtherDao;

	public List<ForOther> findAll() {
		return forOtherDao.findAll();
	}

	public ForOther find( int id ) {
		return forOtherDao.find( id );
	}

	public void save( ForOther forOther ) {
		forOtherDao.save( forOther );
	}

	public void update( ForOther forOther ) {
		forOtherDao.update( forOther );
	}

	public ResponseEntity validate( ForOther forOther ) {
		// Name can't be null
		if ( forOther.getDescription() == null || forOther.getDescription().equals( "" ) )
			return new ResponseEntity( "You must provide a description", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<ForOther> search( String query ) {
		return forOtherDao.search( query );
	}
}
