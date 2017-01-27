package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.RoleGroupDao;
import uk.me.richardcook.sinatra.generator.model.BookFile;
import uk.me.richardcook.sinatra.generator.model.RoleGroupJoined;
import uk.me.richardcook.sinatra.generator.model.RoleView;

import java.util.ArrayList;
import java.util.List;

@Service
class BookRoleGroupService {

	private static final int NUMBER_PER_COLUMN = 36;

	@Autowired
	private RoleGroupDao roleGroupDao;

	void createAnnotationList( BookFile book ) {
		book.printChapter( book.getBookString( "abbreviations" ) );

		// Need to process the roles to put them into three columns in the file
		List<String> column1 = new ArrayList<String>();
		List<String> column2 = new ArrayList<String>();
		List<String> column3 = new ArrayList<String>();

		// Get total count of roles, and total count of role groups, to calculate the number of columns needed
		int totalCount = roleGroupDao.getRoleCount() + roleGroupDao.getRoleGroupCount();

		List<String> columnToAddTo = column1;
		int numberSoFar = 0;
		for ( RoleGroupJoined roleGroup : roleGroupDao.findAllJoined() ) {
			columnToAddTo.add( BookFile.createMultiColumn( 2, BookFile.createBoldText( roleGroup.getName() ) ) );

			if ( columnToAddTo.size() >= NUMBER_PER_COLUMN ) {
				if ( columnToAddTo.equals( column1 ) )
					columnToAddTo = column2;
				else
					columnToAddTo = column3;
			}

			numberSoFar++;

			for ( RoleView role : roleGroup.getRoles() ) {
				columnToAddTo.add( BookFile.createTabJoin( role.getAbbreviation(), role.getName() ) );
				if ( columnToAddTo.size() >= NUMBER_PER_COLUMN ) {
					if ( columnToAddTo.equals( column1 ) )
						columnToAddTo = column2;
					else
						columnToAddTo = column3;
				}

				numberSoFar++;

				if ( numberSoFar == 3 * NUMBER_PER_COLUMN ) {
					printPage( book, column1, column2, column3 );
					column1 = new ArrayList<String>();
					column2 = new ArrayList<String>();
					column3 = new ArrayList<String>();
					columnToAddTo = column1;
					book.printNewPage();
					numberSoFar = 0;
				}
			}
		}
		printPage( book, column1, column2, column3 );

	}

	private void printPage( BookFile book, List<String> column1, List<String> column2, List<String> column3 ) {
		book.printBeginFootnoteSize();
		book.printSuperTabular( 6 );

		int totalRows = column1.size();
		if ( column2.size() > totalRows )
			totalRows = column2.size();
		if ( column3.size() > totalRows )
			totalRows = column3.size();

		for ( int i = 0; i < totalRows; i++ ) {
			String col1 = column1.size() > i ? column1.get( i ) : BookFile.createTabJoin( "", "" );
			String col2 = column2.size() > i ? column2.get( i ) : BookFile.createTabJoin( "", "" );
			String col3 = column3.size() > i ? column3.get( i ) : BookFile.createTabJoin( "", "" );

			book.printTabJoin( col1, col2, col3 );
		}

		book.printEndSuperTabular();
		book.printEndFootnoteSize();
	}
}
