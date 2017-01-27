package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.me.richardcook.sinatra.generator.model.*;
import uk.me.richardcook.sinatra.generator.service.*;

import java.util.List;

@RestController
@RequestMapping( value = "/api/autocomplete" )
public class AutoCompleteController {

	@Autowired
	private SongService songService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private WithService withService;

	@Autowired
	private SourceService sourceService;

	@Autowired
	private StudioService studioService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private LabelService labelService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private ForOtherService forOtherService;

	@Autowired
	private ReleaseService releaseService;

	@Autowired
	private TelevisionService televisionService;

	@Autowired
	private RadioService radioService;

	@Autowired
	private SessionService sessionService;

	@RequestMapping( value = "/song", method = RequestMethod.GET )
	public AutoCompleteObject searchSong( @RequestParam( name = "query" ) String query ) {
		List<Song> songs = songService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Song song : songs ) {
			autoCompleteObject.addValueDataPair( song.getShortTitle(), Integer.toString( song.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/role", method = RequestMethod.GET )
	public AutoCompleteObject searchRole( @RequestParam( name = "query" ) String query ) {
		List<Role> roles = roleService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Role role : roles ) {
			autoCompleteObject.addValueDataPair( role.getName(), Integer.toString( role.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/person", method = RequestMethod.GET )
	public AutoCompleteObject searchPerson( @RequestParam( name = "query" ) String query ) {
		List<Person> persons = personService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Person person : persons ) {
			autoCompleteObject.addValueDataPair( person.toString(), Integer.toString( person.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/location", method = RequestMethod.GET )
	public AutoCompleteObject searchLocation( @RequestParam( name = "query" ) String query ) {
		List<Location> locations = locationService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Location location : locations ) {
			autoCompleteObject.addValueDataPair( location.getName(), Integer.toString( location.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/with", method = RequestMethod.GET )
	public AutoCompleteObject searchWith( @RequestParam( name = "query" ) String query ) {
		List<With> withs = withService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( With with : withs ) {
			autoCompleteObject.addValueDataPair( with.getName(), Integer.toString( with.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/source", method = RequestMethod.GET )
	public AutoCompleteObject searchSource( @RequestParam( name = "query" ) String query ) {
		List<Source> sources = sourceService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Source source : sources ) {
			autoCompleteObject.addValueDataPair( source.getName(), Integer.toString( source.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/studio", method = RequestMethod.GET )
	public AutoCompleteObject searchStudio( @RequestParam( name = "query" ) String query ) {
		List<Studio> studios = studioService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Studio studio : studios ) {
			autoCompleteObject.addValueDataPair( studio.getName(), Integer.toString( studio.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/album", method = RequestMethod.GET )
	public AutoCompleteObject searchAlbum( @RequestParam( name = "query" ) String query ) {
		List<Album> albums = albumService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Album album : albums ) {
			autoCompleteObject.addValueDataPair( album.getTitle(), Integer.toString( album.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/label", method = RequestMethod.GET )
	public AutoCompleteObject searchLabel( @RequestParam( name = "query" ) String query ) {
		List<Label> labels = labelService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Label label : labels ) {
			autoCompleteObject.addValueDataPair( label.getName(), Integer.toString( label.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/film", method = RequestMethod.GET )
	public AutoCompleteObject searchFilm( @RequestParam( name = "query" ) String query ) {
		List<Film> films = filmService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Film film : films ) {
			autoCompleteObject.addValueDataPair( film.getTitle(), Integer.toString( film.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/other", method = RequestMethod.GET )
	public AutoCompleteObject searchForOther( @RequestParam( name = "query" ) String query ) {
		List<ForOther> forOthers = forOtherService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( ForOther forOther : forOthers ) {
			autoCompleteObject.addValueDataPair( forOther.getDescription(), Integer.toString( forOther.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/release", method = RequestMethod.GET )
	public AutoCompleteObject searchRelease( @RequestParam( name = "query" ) String query ) {
		List<Release> releases = releaseService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Release release : releases ) {
			autoCompleteObject.addValueDataPair( release.getTitle(), Integer.toString( release.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/television", method = RequestMethod.GET )
	public AutoCompleteObject searchTelevision( @RequestParam( name = "query" ) String query ) {
		List<Television> televisions = televisionService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Television television : televisions ) {
			autoCompleteObject.addValueDataPair( television.getTitle(), Integer.toString( television.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/radio", method = RequestMethod.GET )
	public AutoCompleteObject searchRadio( @RequestParam( name = "query" ) String query ) {
		List<Radio> radios = radioService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Radio radio : radios ) {
			autoCompleteObject.addValueDataPair( radio.getTitle(), Integer.toString( radio.getId() ) );
		}
		return autoCompleteObject;
	}

	@RequestMapping( value = "/session", method = RequestMethod.GET )
	public AutoCompleteObject searchSession( @RequestParam( name = "query" ) String query ) {
		List<Session> sessions = sessionService.search( query );
		AutoCompleteObject autoCompleteObject = new AutoCompleteObject();
		for ( Session session : sessions ) {
			autoCompleteObject.addValueDataPair( session.getDateDisplay(), Integer.toString( session.getId() ) );
		}
		return autoCompleteObject;
	}
}
