package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionTypeDao;
import uk.me.richardcook.sessionography.model.SessionType;

import java.util.List;


@Service
public class SessionTypeService {

	@Autowired
	private SessionTypeDao sessionTypeDao;

	public List<SessionType> findAll() {
		return sessionTypeDao.findAll();
	}

	public SessionType find( int id ) {
		return sessionTypeDao.find( id );
	}

	public void save( SessionType sessionType ) {
		sessionTypeDao.save( sessionType );
	}

	public void update( SessionType sessionType ) {
		sessionTypeDao.update( sessionType );
	}

	public ResponseEntity validate( SessionType sessionType ) {
		// Name can't be null
		if ( sessionType.getName() == null || sessionType.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		SessionType sessionType1 = sessionTypeDao.findByName( sessionType.getName() );
		if ( sessionType1 != null && sessionType.getId() != sessionType1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}
}
