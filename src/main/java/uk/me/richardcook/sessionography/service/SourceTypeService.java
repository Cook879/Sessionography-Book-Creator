package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SourceTypeDao;
import uk.me.richardcook.sessionography.model.SourceType;

import java.util.List;


@Service
public class SourceTypeService {

	@Autowired
	private SourceTypeDao sourceTypeDao;

	public List<SourceType> findAll() {
		return sourceTypeDao.findAll();
	}

	public SourceType find( int id ) {
		return sourceTypeDao.find( id );
	}

	public void save( SourceType sourceType ) {
		sourceTypeDao.save( sourceType );
	}

	public void update( SourceType sourceType ) {
		sourceTypeDao.update( sourceType );
	}

	public ResponseEntity validate( SourceType sourceType ) {
		// Name can't be null
		if ( sourceType.getName() == null || sourceType.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		SourceType sourceType1 = sourceTypeDao.findByName( sourceType.getName() );
		if ( sourceType1 != null && sourceType.getId() != sourceType1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}
}
