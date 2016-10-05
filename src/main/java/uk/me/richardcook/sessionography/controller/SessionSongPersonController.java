package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.PersonRoleNote;
import uk.me.richardcook.sessionography.model.SessionSong;
import uk.me.richardcook.sessionography.model.SessionSongPerson;
import uk.me.richardcook.sessionography.model.SessionSongPersonJoined;
import uk.me.richardcook.sessionography.service.PersonRoleNoteService;
import uk.me.richardcook.sessionography.service.SessionSongPersonService;
import uk.me.richardcook.sessionography.service.SessionSongService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( value = "/api/session_song_person" )
public class SessionSongPersonController {

	@Autowired
	private SessionSongPersonService sessionSongPersonService;
	@Autowired
	private SessionSongService sessionSongService;

	@Autowired
	private PersonRoleNoteService personRoleNoteService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SessionSongPerson get( @PathVariable int id ) {
		return sessionSongPersonService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SessionSongPerson> getAll() {
		return sessionSongPersonService.findAll();
	}

	@RequestMapping( value = "/session/{id}", method = RequestMethod.GET )
	public Object getForSession( @PathVariable int id ) {
		// TODO move to service
		List<SessionSongPersonJoined> sessionSongPersonJoineds = sessionSongPersonService.findForSession( id );

		Map<Integer, Map<Integer, SessionSongPersonJoined>> personMap = new HashMap<Integer, Map<Integer, SessionSongPersonJoined>>();

		for ( SessionSongPersonJoined sessionSongPersonJoined : sessionSongPersonJoineds ) {
			int person = sessionSongPersonJoined.getPerson().getId();
			int role = sessionSongPersonJoined.getRole().getId();
			if ( personMap.containsKey( person ) ) {
				Map<Integer, SessionSongPersonJoined> roleMap = personMap.get( person );
				if ( roleMap.containsKey( role ) ) {
					roleMap.get( role ).addSessionSong( sessionSongPersonJoined.getSessionSong() );
				} else {
					sessionSongPersonJoined.addSessionSong( sessionSongPersonJoined.getSessionSong() );
					roleMap.put( role, sessionSongPersonJoined );
				}
			} else {
				Map<Integer, SessionSongPersonJoined> roleMap = new HashMap<Integer, SessionSongPersonJoined>();
				sessionSongPersonJoined.addSessionSong( sessionSongPersonJoined.getSessionSong() );
				roleMap.put( role, sessionSongPersonJoined );
				personMap.put( person, roleMap );
			}
		}

		List<SessionSongPersonJoined> finalSessionSongPersonJoineds = new ArrayList<SessionSongPersonJoined>();
		for ( Map<Integer, SessionSongPersonJoined> roleMap : personMap.values() ) {
			for ( SessionSongPersonJoined sessionSongPersonJoined : roleMap.values() ) {
				finalSessionSongPersonJoineds.add( sessionSongPersonJoined );
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "sessionSongPersons", finalSessionSongPersonJoineds );
		map.put( "notes", getNotesForSession( id ) );
		List<SessionSong> sessionSongs = sessionSongService.findForSession( id );
		map.put( "sessionSongs", sessionSongs );
		return map;
	}

	@RequestMapping( value = "/session/{id}/notes", method = RequestMethod.GET )
	public List<PersonRoleNote> getNotesForSession( @PathVariable int id ) {
		return personRoleNoteService.findForSession( id );
	}


	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SessionSongPerson sessionSongPerson ) {
		ResponseEntity responseEntity = sessionSongPersonService.validate( sessionSongPerson );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongPersonService.save( sessionSongPerson );

		// Let's check it saved
		SessionSongPerson sessionSongPerson1 = sessionSongPersonService.find( sessionSongPerson.getId() );
		if ( sessionSongPerson1 != null )
			return sessionSongPerson1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SessionSongPerson sessionSongPerson ) {
		if ( sessionSongPerson.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SessionSongPerson sessionSongPerson1 = sessionSongPersonService.find( id );
		if ( sessionSongPerson1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( sessionSongPerson.equals( sessionSongPerson1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sessionSongPersonService.validate( sessionSongPerson );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongPersonService.update( sessionSongPerson );

		SessionSongPerson sessionSongPerson2 = sessionSongPersonService.find( id );
		if ( sessionSongPerson2.equals( sessionSongPerson ) )
			return sessionSongPerson2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/session/{id}", method = RequestMethod.DELETE )
	public Object deleteForSession( @PathVariable int id ) {
		sessionSongPersonService.deleteForSession( id );
		return new ResponseEntity( "Deleted all people details for session " + id, HttpStatus.OK );
	}
}
