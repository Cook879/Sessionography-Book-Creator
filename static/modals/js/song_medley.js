
$( document ).ready( function () {

	// i is the number of people so far
	var i = 1;

	var $id = $( '#modal-song-medley-session-song-id' ).val();
	var $callerId = $( '#modal-song-medley-caller' ).val();
	var $sessionSong = $( '#modal-song-medley-session-song-id' ).val();

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/session_song_song/session_song/' + $id, function ( result ) {
			$.each( result, function ( index, value ) {
				makeTableRow();

				$( '#modal-song-medley-title-' + i ).val( value.song.shortTitle );
				$( '#modal-song-medley-id-' + i ).val( value.song.id );
				$( '#modal-song-medley-position-' + i ).val( value.position );
				$( '#modal-song-medley-parody-' + i ).prop( 'checked', value.parody );

				i++;
			} );
		} );
	} else {
		$( '#modal-song-medley-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.attr( 'colspan', 4 )
					.text( 'Error - need to provide a session song id' ) ) );


	}

	function makeTableRow() {
		var j = i;
		$( '#modal-song-medley-table' ).find( 'tbody' )
			.append( $( '<tr id="modal-song-medley-table-row-' + j + '">' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-song-medley-id" )
						.attr( "id", "modal-song-medley-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-song-medley-title" )
						.attr( "id", "modal-song-medley-title-" + j )
					)
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-song" ).load( 'song.html', function () {
								$( '#modal-song-id' ).val( $( '#modal-song-medley-id-' + j ).val() );
								$( '#modal-song-caller-type' ).val( "medley" );
								$( '#modal-song-caller' ).val( j );
								$.getScript( 'js/song.js' );
								$( '#song-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-song" ).load( 'song.html', function () {
								$( '#modal-song-caller-type' ).val( "medley" );
								$( '#modal-song-caller' ).val( j );
								$.getScript( 'js/song.js' );
								$( '#song-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-song btn" )
						.attr( "id", "btn-song-delete-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-trash" )
						)
						.click( function () {
							$( "#modal-song-medley-table-row-" + j ).remove();
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-song-medley-position" )
						.attr( "id", "modal-song-medley-position-" + j )
						.attr( "type", "number" )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-song-medley-parody" )
						.attr( "id", "modal-song-medley-parody-" + j )
						.attr( "type", "checkbox" )
					)
				)
			);
		$( '#modal-song-medley-title-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/song',
			onSelect: function ( suggestion ) {
				$( '#modal-song-medley-id-' + j ).val( suggestion.data );
			}
		} );
	}

	$( '#modal-song-medley-add' ).click( function () {
		makeTableRow();
		i++;
	} );

	$( '#modal-song-medley-submit' ).click( function () {
		$( '#modal-body-success-medley' ).empty();
		deleteSong();
	} );

	function deleteSong() {
		$.ajax( {
			url: '/api/session_song_song/session_song/' + $sessionSong,
			type: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			success: function ( result, status, data ) {
				$( '#modal-body-success-medley' ).append( $( '<p>' ).text( "Deleted all songs for session song " + $sessionSong ) );
				submitSongs();
			},
			error: function ( result, status, xhr ) {
				$( '#modal-body-success-medley' ).append( $( '<p>' ).text( "Deleted all songs for session song " + $sessionSong ) );
				submitSongs();
			}
		} );
	}


	function submitSongs() {
		for ( var k = 1; k < i; k++ ) {
			var position = $( '#modal-song-medley-position-' + k ).val();
			var song = $( '#modal-song-medley-id-' + k ).val();
			var parody = $( '#modal-song-medley-parody-' + k ).prop( 'checked' );

			if ( song != null && song != "" ) {
				var object = { "sessionSong": $sessionSong, "song": song, "position": position, "parody": parody };
				ajaxRequest( '/api/session_song_song', 'POST', object, $( '#modal-song-medley-success' ), $( '#modal-song-medley-error' ), processSubmit );
			}
		}
	}

	function processSubmit( data ) {
		$( '#modal-body-success-medley' ).append( $( '<p>' ).text( "Song with id " + data.song + ", position " + data.position +
			" added to session song " + data.sessionSong + ", id is " + data.id ) );
		$( '#modal-session-song-song-id-' + $callerId ).val( null );
		$( '#modal-session-song-song-' + $callerId ).val( "Medley/Parody" );
		$( '#modal-session-song-medley-' + $callerId ).prop( 'checked', true );
		$( '#modal-session-song-song-id-' + $callerId ).attr( 'disabled', true );
		$( '#modal-session-song-song-' + $callerId ).attr( 'disabled', true );
		$( '#btn-song-edit-' + $callerId ).attr( 'disabled', true );
		$( '#btn-song-new-' + $callerId ).attr( 'disabled', true );


	}
} );