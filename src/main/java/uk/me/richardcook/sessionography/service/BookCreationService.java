package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.BookStringDao;
import uk.me.richardcook.sessionography.model.BookFile;
import uk.me.richardcook.sessionography.model.BookString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookCreationService {

	@Autowired
	private BookSectionService bookSectionService;

	@Autowired
	private BookRoleGroupService bookRoleGroupService;

	@Autowired
	private BookPersonService bookPersonService;

	@Autowired
	private BookSongService bookSongService;

	@Autowired
	private BookStringDao bookStringDao;

	public void createMainBook() throws Exception {
		BookFile file = new BookFile( "book.tex", getBookStrings() );
		file.setUpDocument( null, false );

		bookSectionService.writeAll( file );

		file.endDocument();
		file.closeFile();
	}

	public void createPersonBook() throws Exception {
		BookFile file = new BookFile( "people.tex", getBookStrings() );
		file.setUpDocument( file.getBookString( "people-subtitle" ), true );

		bookRoleGroupService.createAnnotationList( file );

		file.printNewPage();
		file.printChapter( file.getBookString( "musicians" ) );
		bookPersonService.generatePersons( file, BookPersonService.PERSON_MUSICIAN );
		file.printChapter( file.getBookString( "groups" ) );
		bookPersonService.generatePersons( file, BookPersonService.PERSON_GROUP );
		file.printChapter( file.getBookString( "arrangers" ) );
		bookPersonService.generatePersons( file, BookPersonService.PERSON_ARRANGER );
		file.printChapter( file.getBookString( "songwriters" ) );
		bookPersonService.generateSongPersons( file );

		file.endDocument();
		file.closeFile();
	}

	public void createSongBook() throws Exception {
		BookFile file = new BookFile( "song.tex", getBookStrings() );
		file.setUpDocument( file.getBookString( "song-subtitle" ), false );

		bookSongService.generateSongs( file );

		file.endDocument();
		file.closeFile();
	}

	public Map<String, String> getBookStrings() {
		List<BookString> bookStrings = bookStringDao.findAll();

		Map<String, String> map = new HashMap<String, String>();

		for ( BookString bookString : bookStrings ) {
			map.put( bookString.getName(), bookString.getValue() );
		}

		return map;
	}
}
