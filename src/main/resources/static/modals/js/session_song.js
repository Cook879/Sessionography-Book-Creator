$( document ).ready( function () {

	// i is the number of session-songs so far
	var i = 1;

	var $id = GetURLParameter( "id" );

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/session_song/session/' + $id, function ( result ) {
			$.each( result, function ( index, value ) {
				makeTableRow();

				$( '#modal-session-song-id-' + i ).val( value.id );
				$( '#modal-session-song-master-' + i ).val( value.master );
				$( '#modal-session-song-notes-' + i ).val( value.notes );
				$( '#modal-session-song-position-' + i ).val( value.position );
				$( '#modal-session-song-reprise-' + i ).prop( 'checked', value.reprise );

				var songList = value.sessionSongSongs;

				var j = i;
				if ( songList.length > 1 || songList.length == 1 && songList[ 0 ].parody ) {
					// TODO medleys
					$( '#modal-session-song-medley-' + j ).prop( 'checked', true );
					$( '#btn-song-medley-' + j ).attr( 'disabled', false );
					$( '#modal-session-song-song-' + j ).val( 'Medley/Parody' );
				} else {
					if ( songList.length == 1 ) {
						$.getJSON( '/api/song/' + songList[ 0 ].song, function ( result ) {
							$( '#modal-session-song-song-id-' + j ).val( songList[ 0 ].song );
							$( '#modal-session-song-song-' + j ).val( result.shortTitle );
						} );
					}
					$( '#modal-session-song-song-id-' + j ).attr( 'disabled', false );
					$( '#modal-session-song-song-' + j ).attr( 'disabled', false );
					$( '#btn-song-edit-' + j ).attr( 'disabled', false );
					$( '#btn-song-new-' + j ).attr( 'disabled', false );
					$( '#btn-song-medley-' + j ).attr( 'disabled', false );
				}
				i++;
			} );
		} );
	} else {
		var defaultRows = 4;
		for ( j = 0; j < defaultRows; j++ ) {
			makeTableRow();
			i++;
		}
	}


	function makeTableRow() {
		var j = i;
		$( '#modal-session-song-table' ).find( 'tbody' )
			.append( $( '<tr id="modal-session-song-table-row-' + j + '">' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-id" )
						.attr( "id", "modal-session-song-id-" + j )
						.attr( "hidden", true )
					).append( $( '<input>' )
						.addClass( "modal-session-song-medley" )
						.attr( "id", "modal-session-song-medley-" + j )
						.attr( "hidden", true )
						.attr( "type", "checkbox" )
					)
					.append( $( '<input>' )
						.addClass( "modal-session-song-song-id" )
						.attr( "id", "modal-session-song-song-id-" + j )
						.attr( "hidden", true )
						.attr( "disabled", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-session-song-song" )
						.attr( "id", "modal-session-song-song-" + j )
						.attr( "type", "text" )
						.attr( "disabled", true )
					)
					.append( $( '<button>' )
						.addClass( "btn-song btn" )
						.attr( "id", "btn-song-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.attr( "disabled", true )
						.click( function () {
							$( "#div-modal-song" ).load( 'song.html', function () {
								$( '#modal-song-id' ).val( $( '#modal-session-song-song-id-' + j ).val() );
								$( '#modal-song-caller' ).val( j );
								$.getScript( 'js/song.js' );
								$( '#song-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-song btn" )
						.attr( "id", "btn-song-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.attr( "disabled", true )
						.click( function () {
							$( "#div-modal-song" ).load( 'song.html', function () {
								$( '#modal-song-caller' ).val( j );
								$.getScript( 'js/song.js' );
								$( '#song-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-song-medley btn" )
						.attr( "id", "btn-song-medley-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.text( "Medley/Parody" )
						.attr( "tabindex", -1 )
						.attr( "disabled", true )
						.click( function () {
							$( "#div-modal-song-medley" ).load( 'song_medley.html', function () {
								$( '#modal-song-medley-session-song-id' ).val( $( '#modal-session-song-id-' + j ).val() );
								$( '#modal-song-medley-caller' ).val( j );
								$.getScript( 'js/song_medley.js' );
								$( '#song-medley-modal' ).modal( 'show' );
							} );
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-master" )
						.attr( "id", "modal-session-song-master-" + j )
						.attr( "type", "text" )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-position" )
						.attr( "id", "modal-session-song-position-" + j )
						.attr( "type", "number" )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-reprise" )
						.attr( "id", "modal-session-song-reprise-" + j )
						.attr( "type", "checkbox" )
					)
				)
				.append( $( '<td>' )
					.append( $( '<textarea>' )
						.addClass( "modal-session-song-notes" )
						.attr( "id", "modal-session-song-notes-" + j )
						.attr( "tabindex", -1 )
					)
				)
				.append( $( '<td>' )
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.attr( "tabindex", -1 )
						.text( "Edit Takes" )
						.click( function () {
							if ( $( "#modal-session-song-id-" + j ).val() != null && $( "#modal-session-song-id-" + j ).val() != '' ) {
								$( "#div-modal-session-song-take" ).load( 'session_song_take.html', function () {
									$( '#modal-session-song-take-session-song-id' ).val( $( '#modal-session-song-id-' + j ).val() );
									$( '#modal-session-song-take-caller' ).val( j );
									$.getScript( 'js/session_song_take.js' );
									$( '#session-song-take-modal' ).modal( 'show' );
								} );
							} else {
								alert( "You need to save the session songs before you can add takes." );
							}
						} )
					)
				)
				.append( $( '<button>' )
					.addClass( "btn-session-song btn" )
					.attr( "id", "btn-session-song-delete-" + j )
					.attr( "type", "button" )
					.attr( "data-toggle", "modal" )
					.attr( "tabindex", -1 )
					.append( $( '<span>' )
						.addClass( "glyphicon glyphicon-trash" )
					)
					.click( function () {
						if ( confirm( "Are you sure you want to delete this session song? This can't be undone!" ) ) {
							var id = $( '#modal-session-song-id-' + j ).val();
							if ( id != null ) {
								$.ajax( {
									url: '/api/session_song/' + id,
									type: 'DELETE',
									headers: {
										'Accept': 'application/json',
										'Content-Type': 'application/json'
									},
									success: function ( result, status, data ) {
										$( "#modal-session-song-table-row-" + j ).remove();
									},
									error: function ( result, status, xhr ) {
										$( "#modal-session-song-table-row-" + j ).remove();
									}
								} );
							} else {
								$( "#modal-session-song-table-row-" + j ).remove();
							}

						}
					} )
				)
			);
		$( '#modal-session-song-song-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/song',
			onSelect: function ( suggestion ) {
				$( '#modal-session-song-song-id-' + j ).val( suggestion.data );
			}
		} );
	}

	$( '#modal-session-song-add' ).click( function () {
		makeTableRow();
		i++;
	} );

	$( '#modal-session-song-submit' ).click( function () {
		$( '.modal-body-success' ).empty();
		var session = $id;
		for ( var k = 1; k < i; k++ ) {
			var position = $( '#modal-session-song-position-' + k ).val();
			var master = $( '#modal-session-song-master-' + k ).val();
			var notes = $( '#modal-session-song-notes-' + k ).val();
			var reprise = $( '#modal-session-song-reprise-' + k ).prop( 'checked' );

			var id = $( '#modal-session-song-id-' + k ).val();

			var object = {
				"session": session,
				"master": master,
				"notes": notes,
				"position": position,
				"reprise": reprise
			};

			if ( id == "" ) {
				object[ 'id' ] = null;
				ajaxRequest( '/api/session_song', 'POST', object, $( '#modal-session-song-success' ), $( '#modal-session-song-error' ), processSubmit );
			} else {
				object[ 'id' ] = id;
				ajaxRequest( '/api/session_song/' + id, 'PUT', object, $( '#modal-session-song-success' ), $( '#modal-session-song-error' ), processSubmit );

				if ( !$( '#modal-session-song-medley-' + k ).prop( 'checked' ) ) {
					deleteSong( id, k );
				}
				// Medleys should be saved elsewhere
			}
		}
	} );

	function deleteSong( localId, localK ) {
		$.ajax( {
			url: '/api/session_song_song/session_song/' + localId,
			type: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			success: function ( result, status, data ) {
				$( '.modal-body-success' ).append( $( '<p>' ).text( "Deleted all songs for session song " + localId ) );
				submitSong( localK, localId );
			},
			error: function ( result, status, xhr ) {
				$( '.modal-body-success' ).append( $( '<p>' ).text( "Deleted all songs for session song " + localId ) );
				submitSong( localK, localId );
			}
		} );
	}


	function submitSong( k, session_song ) {
		var song = $( '#modal-session-song-song-id-' + k ).val();
		var position = 1;

		if ( song != null && song != "" ) {
			var object = { "song": song, "position": position, "sessionSong": session_song };
			ajaxRequest( '/api/session_song_song', 'POST', object, $( '#modal-session-song-success' ), $( '#modal-session-song--error' ), processSubmitSong );

		}
	}

	function processSubmitSong( data ) {
		$( '.modal-body-success' ).append( $( '<p>' ).text( "Song with id " + data.song + ", position " + data.position +
			" added to session song " + data.sessionSong + ", id is " + data.id ) );
	}

	function processSubmit( data ) {
		$( '.modal-body-success' ).append( $( '<p>' ).text( "Session song with position " + data.position + " saved, id is " + data.id ) );
	}

} );