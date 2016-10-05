$( document ).ready( function () {

	// i is the number of people so far

	var $sessionId = $( '#modal-session-song-person-exceptions-id' ).val()
	var $callerId = $( '#modal-session-song-person-exceptions-caller' ).val();
	var $values = $( '#modal-session-song-person-exceptions-values' ).val();
	var $songs = $values.split( "-" );
	var $ids = [];

	$.getJSON( '/api/session_song/session/' + $sessionId, function ( result ) {

		var letters = mapLetters( result );

		$.each( result, function ( index, value ) {
			$ids.push( value.id );
			makeCheckBox( value.id );

			$( '#modal-session-song-person-exceptions-id-' + value.id ).val( value.id );
			$( '#modal-session-song-person-exceptions-check-' + value.id ).prop( 'checked', $.inArray( value.id.toString(), $songs ) > -1 );
			$( '#modal-session-song-person-exceptions-letter-' + value.id ).text( letters[ value.id ] );
		} );

		if( $songs[0] == "" ) {
			$.each( $ids, function(index,value) {
				$( '#modal-session-song-person-exceptions-check-' + value ).prop( 'checked', true );
			});
		}
	} );

	function makeCheckBox( j ) {
		$( '#modal-session-song-person-exceptions-checklist' )
			.append( $( '<input>' )
				.addClass( "modal-session-song-person-exceptions-id" )
				.attr( "id", "modal-session-song-person-exceptions-id-" + j )
				.attr( "hidden", true )
			)
			.append( $( '<input>' )
				.addClass( "modal-session-song-person-exceptions-check" )
				.attr( "id", "modal-session-song-person-exceptions-check-" + j )
				.attr( "type", "checkbox" )
			)
			.append( $( '<label>' )
				.addClass( "modal-session-song-person-exceptions-letter" )
				.attr( "id", "modal-session-song-person-exceptions-letter-" + j )
			)
			.append( $( '<br/>' ) )
		;
	}

	function mapLetters( sessionSongs ) {
		var result = [];
		$.each( sessionSongs, function ( index, value ) {
			result[ value.id ] = getCharForNumber( index );
		} );
		return result;
	}

	$( '#modal-session-song-person-exceptions-submit' ).click( function () {
		var result = '';
		$.each( $ids, function ( index, value ) {
			var checked = $( '#modal-session-song-person-exceptions-check-' + value ).prop( 'checked' );
			if( checked ) {
				if ( result != '' )
					result += '-';
				result += value;
			}
		} );
		$('#modal-session-song-person-songs-'+$callerId).val(result);
		$( "#modal-session-song-person-exceptions-cancel" ).trigger( "click" );

	} );

	function submit() {
		for ( var k = 1; k < i; k++ ) {
			var person = $( '#modal-session-song-person-person-id-' + k ).val();
			var role = $( '#modal-session-song-person-role-id-' + k ).val();

			if ( person == null || role == null ) {
				continue;
			}

			var object = { "person": person, "role": role };

			var songsStr = $( '#modal-session-song-person-songs-' + k ).val();
			var songs = songsStr.split( "-" );

			for ( var p = 0; p < songs.length; p++ ) {
				object[ 'sessionSong' ] = songs[ p ];
				ajaxRequest( '/api/session_song_person', 'POST', object, $( '#modal-session-song-person-success' ), $( '#modal-session-song-person-error' ), processSubmit );
			}
		}
	}

	function processSubmit( data ) {
		$( '.modal-body-success' ).append( $( '<p>' ).text( "Session song person with person " + data.person + ", role " + data.role +
			" and session song " + data.sessionSong + " saved, id is " + data.id ) );
	}

} );