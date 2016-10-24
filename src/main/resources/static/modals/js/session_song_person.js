$( document ).ready( function () {

	// i is the number of people so far
	var i = 1;

	var $id = GetURLParameter( "id" );

	var notes = [];
	var sessionSongs = [];

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/session_song_person/session/' + $id, function ( result ) {
			$.each( result.sessionSongs, function ( index, value ) {
				sessionSongs.push( value.id );
			} );

			notes = result.notes;

			if ( result.sessionSongPersons.length == 0 ) {
				for ( var p = 0; p < 30; p++ ) {
					createNewTableRow();
				}
			}
			$.each( result.sessionSongPersons, function ( index, value ) {
				makeTableRow( notes );

				$( '#modal-session-song-person-role-' + i ).val( value.role.name );
				$( '#modal-session-song-person-role-id-' + i ).val( value.role.id );
				var person = value.person;
				if ( person.firstName != null )
					$( '#modal-session-song-person-person-' + i ).val( person.firstName + " " + person.lastName );
				else
					$( '#modal-session-song-person-person-' + i ).val( person.lastName );
				$( '#modal-session-song-person-person-id-' + i ).val( person.id );

				// Work out what songs they have
				var optionId = '';
				$.each( value.sessionSongs, function ( index, value ) {
					if ( optionId != '' )
						optionId += '-';
					optionId += value;
				} );

				$( '#modal-session-song-person-songs-' + i ).val( optionId );

				if ( value.note != null )
					$( '#modal-session-song-person-note-' + i ).val( value.note.id );

				i++;
			} );
		} );
	} else {
		$( '#modal-session-song-person-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.attr( 'colspan', 4 )
					.text( 'Error - need to provide an id' ) ) );
	}
	function makeTableRow( notes ) {
		var j = i;
		$( '#modal-session-song-person-table' ).find( 'tbody' )
			.append( $( '<tr id="modal-session-song-person-table-row-' + j + '">' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-person-person-id" )
						.attr( "id", "modal-session-song-person-person-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-session-song-person-person" )
						.attr( "id", "modal-session-song-person-person-" + j )
						.attr( "type", "text" )
					)
					.append( $( '<button>' )
						.addClass( "btn-person btn" )
						.attr( "id", "btn-person-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-person" ).load( 'person.html', function () {
								$( '#modal-person-id' ).val( $( '#modal-session-song-person-person-id-' + j ).val() );
								$( '#modal-person-caller' ).val( j );
								$.getScript( 'js/person.js' );
								$( '#person-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-person btn" )
						.attr( "id", "btn-person-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-person" ).load( 'person.html', function () {
								$( '#modal-person-caller' ).val( j );
								$.getScript( 'js/person.js' );
								$( '#person-modal' ).modal( 'show' );
							} );
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-person-role-id" )
						.attr( "id", "modal-session-song-person-role-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-session-song-person-role" )
						.attr( "id", "modal-session-song-person-role-" + j )
						.attr( "type", "text" )
					)
					.append( $( '<button>' )
						.addClass( "btn-role btn" )
						.attr( "id", "btn-role-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-role" ).load( 'role.html', function () {
								$( '#modal-role-id' ).val( $( '#modal-session-song-person-role-id-' + j ).val() );
								$( '#modal-role-caller' ).val( j );
								$.getScript( 'js/role.js' );
								$( '#role-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-role btn" )
						.attr( "id", "btn-role-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-role" ).load( 'role.html', function () {
								$( '#modal-role-caller' ).val( j );
								$.getScript( 'js/role.js' );
								$( '#role-modal' ).modal( 'show' );
							} );
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-person-songs" )
						.attr( "id", "modal-session-song-person-songs-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<button>' )
						.addClass( "btn-songs btn" )
						.attr( "id", "btn-songs-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.text( "Add exceptions" )
						.click( function () {
							$( "#div-modal-session-song-person-exceptions" ).load( 'session_song_person_exceptions.html', function () {
								$( '#modal-session-song-person-exceptions-caller' ).val( j );
								$( '#modal-session-song-person-exceptions-id' ).val( $id );
								$( '#modal-session-song-person-exceptions-values' ).val( $( '#modal-session-song-person-songs-' + j ).val() );
								$.getScript( 'js/session_song_person_exceptions.js' );
								$( '#session-song-person-exceptions-modal' ).modal( 'show' );
							} );
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<select>' )
						.addClass( "modal-session-song-person-note" )
						.attr( "id", "modal-session-song-person-note-" + j )
						.attr( "tabindex", -1 )
					)
					.append( $( '<button>' )
						.addClass( "btn-note btn" )
						.attr( "id", "btn-note-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-note" ).load( 'note.html', function () {
								$( '#modal-note-id' ).val( $( '#modal-session-song-person-note-' + j ).val() );
								$( '#modal-note-caller' ).val( j );
								$( '#modal-note-session' ).val( $id );
								$.getScript( 'js/note.js' );
								$( '#note-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-note btn" )
						.attr( "id", "btn-note-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-note" ).load( 'note.html', function () {
								$( '#modal-note-caller' ).val( j );
								$( '#modal-note-session' ).val( $id );
								$.getScript( 'js/note.js' );
								$( '#note-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-delete btn" )
						.attr( "id", "btn-delete-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-trash" )
						)
						.click( function () {
							$( "#modal-session-song-person-table-row-" + j ).remove();
						} )
					)
				)
			);
		$( '#modal-session-song-person-person-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/person',
			onSelect: function ( suggestion ) {
				$( '#modal-session-song-person-person-id-' + j ).val( suggestion.data );
			}
		} );
		$( '#modal-session-song-person-role-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/role',
			onSelect: function ( suggestion ) {
				$( '#modal-session-song-person-role-id-' + j ).val( suggestion.data );
			}
		} );
		var select = $( '#modal-session-song-person-note-' + j );
		select.append( '<option selected></option>' );
		$.each( notes, function ( index, value ) {
			select.append( '<option value=' + value.id + '>' + value.note + '</option>' );
		} );

	}

	$( '#modal-session-song-person-add' ).click( function () {
		createNewTableRow();
	} );

	$( '#modal-session-song-person-refresh' ).click( function () {
		getNotes( null, null );
	} );

	function createNewTableRow() {
		makeTableRow( notes );

		var optionId = '';
		$.each( sessionSongs, function ( index, value ) {
			if ( optionId != '' )
				optionId += '-';
			optionId += value;
		} );

		$( '#modal-session-song-person-songs-' + i ).val( optionId );

		i++;
	}

	$( '#modal-session-song-person-submit' ).click( function () {
		$( '#modal-body-success-session-song-person' ).empty();
		$.ajax( {
			url: '/api/session_song_person/session/' + $id,
			type: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			success: function ( result, status, data ) {
				$( '#modal-body-success-session-song-person' ).append( $( '<p>' ).text( "Deleted all session song person details for this session" ) );
				submit();
			},
			error: function ( result, status, xhr ) {
				$( '#modal-body-success-session-song-person' ).append( $( '<p>' ).text( "Deleted all session song person details for this session" ) );
				submit();
			}
		} );

	} );

	function submit() {
		for ( var k = 1; k < i; k++ ) {
			var person = $( '#modal-session-song-person-person-id-' + k ).val();
			var role = $( '#modal-session-song-person-role-id-' + k ).val();
			var note = $( '#modal-session-song-person-note-' + k ).val();

			if ( person == null || role == null ) {
				continue;
			}

			var object = { "person": person, "role": role, "note": note };

			var songsStr = $( '#modal-session-song-person-songs-' + k ).val();
			var songs = songsStr.split( "-" );

			for ( var p = 0; p < songs.length; p++ ) {
				object[ 'sessionSong' ] = songs[ p ];
				ajaxRequest( '/api/session_song_person', 'POST', object, $( '#modal-session-song-person-success' ), $( '#modal-session-song-person-error' ), processSubmit );
			}
		}
	}

	function processSubmit( data ) {
		$( '#modal-body-success-session-song-person' ).append( $( '<p>' ).text( "Session song person with person " + data.person + ", role " + data.role +
			" and session song " + data.sessionSong + " saved, id is " + data.id ) );
	}

} );

// Stolen from some stackoverflow.... TODO get link
function getCharForNumber( i ) {
	var ordA = 'a'.charCodeAt( 0 );
	var ordZ = 'z'.charCodeAt( 0 );
	var len = ordZ - ordA + 1;

	var s = "";
	while ( i >= 0 ) {
		s = String.fromCharCode( i % len + ordA ) + s;
		i = Math.floor( i / len ) - 1;
	}
	return s;

}

function mapLetters( sessionSongs ) {
	var result = [];
	$.each( sessionSongs, function ( index, value ) {
		result[ value ] = getCharForNumber( index );
	} );
	return result;
}

function getNotes( caller, providedVal ) {
	var $id = GetURLParameter( "id" );

	// Get a list of role_groups
	$( '.modal-session-song-person-note' ).each( function () {
		var select = $( this );
		var selectVal = select.val();

		select.find( 'option' ).remove();
		select.append( '<option selected></option>' );

		$.getJSON( '/api/session_song_person/session/' + $id + '/notes', function ( result ) {

			$.each( result, function ( id, item ) {
				select.append( '<option value="' + item.id + '">' + item.note + '</option>' );
			} );

			select.val( selectVal );

			if ( select.attr( 'id' ) == 'modal-session-song-person-note-' + caller && providedVal != null ) {
				select.val( providedVal );
			}
		} );
	} );
}