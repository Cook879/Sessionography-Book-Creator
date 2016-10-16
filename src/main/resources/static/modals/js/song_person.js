$( document ).ready( function () {

	// i is the number of people so far
	var i = 1;

	var $id = $( '#modal-song-id' ).val();

	var options = [];
	var notes = [];

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/song_person/song/' + $id, function ( result ) {
			$.each( result, function ( index, value ) {
				makeTableRow();

				$( '#modal-song-person-role-' + i ).val( value.role.name );
				$( '#modal-song-person-role-id-' + i ).val( value.role.id );
				var person = value.person;
				if ( person.firstName != null )
					$( '#modal-song-person-person-' + i ).val( person.firstName + " " + person.lastName );
				else
					$( '#modal-song-person-person-' + i ).val( person.lastName );
				$( '#modal-song-person-person-id-' + i ).val( person.id );

				i++;
			} );
		} );
	} else {
		$( '#modal-song-person-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.attr( 'colspan', 4 )
					.text( 'Error - need to provide a song id' ) ) );
	}

	function makeTableRow( options, notes ) {
		var j = i;
		$( '#modal-song-person-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-song-person-id" )
						.attr( "id", "modal-song-person-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-song-person-person-id" )
						.attr( "id", "modal-song-person-person-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-song-person-person" )
						.attr( "id", "modal-song-person-person-" + j )
						.attr( "type", "text" )
					)
					.append( $( '<button>' )
						.addClass( "btn-person btn" )
						.attr( "id", "btn-person-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-person" ).load( 'person.html', function () {
								$( '#modal-person-id' ).val( $( '#modal-song-person-person-id-' + j ).val() );
								$( '#modal-person-caller' ).val( j );
								$( '#modal-person-caller-type' ).val( "song" );
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
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-person" ).load( 'person.html', function () {
								$( '#modal-person-caller' ).val( j );
								$( '#modal-person-caller-type' ).val( "song" );
								$.getScript( 'js/person.js' );
								$( '#person-modal' ).modal( 'show' );
							} );
						} )
					)
				)
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-song-person-role-id" )
						.attr( "id", "modal-song-person-role-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-song-person-role" )
						.attr( "id", "modal-song-person-role-" + j )
						.attr( "type", "text" )
					)
					.append( $( '<button>' )
						.addClass( "btn-role btn" )
						.attr( "id", "btn-role-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-role" ).load( 'role.html', function () {
								$( '#modal-role-id' ).val( $( '#modal-song-person-role-id-' + j ).val() );
								$( '#modal-role-caller' ).val( j );
								$( '#modal-person-caller-type' ).val( "song" );
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
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-role" ).load( 'role.html', function () {
								$( '#modal-role-caller' ).val( j );
								$( '#modal-person-caller-type' ).val( "song" );
								$.getScript( 'js/role.js' );
								$( '#role-modal' ).modal( 'show' );
							} );
						} )
					)
				)
			);

		$( '#modal-song-person-person-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/person',
			onSelect: function ( suggestion ) {
				$( '#modal-song-person-person-id-' + j ).val( suggestion.data );
			}
		} );
		$( '#modal-song-person-role-' + j ).devbridgeAutocomplete( {
			serviceUrl: '/api/autocomplete/role',
			onSelect: function ( suggestion ) {
				$( '#modal-song-person-role-id-' + j ).val( suggestion.data );
			}
		} );
	}

	$( '#modal-song-person-add' ).click( function () {
		makeTableRow( options, notes );
		i++;
	} );

	$( '#modal-song-person-submit' ).click( function () {
		$( '.modal-body-success' ).empty();
		$.ajax( {
			url: '/api/song_person/song/' + $id,
			type: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			success: function ( result, status, data ) {
				$( '.modal-body-success' ).append( $( '<p>' ).text( "Deleted all song person details for this song" ) );
				submit();
			},
			error: function ( result, status, xhr ) {
				$( '.modal-body-success' ).append( $( '<p>' ).text( "Deleted all song person details for this song" ) );
				submit();
			}
		} );

	} );

	function submit() {
		for ( var k = 1; k < i; k++ ) {
			var person = $( '#modal-song-person-person-id-' + k ).val();
			var role = $( '#modal-song-person-role-id-' + k ).val();
			var notes = $( '#modal-song-person-note-' + k ).val();

			if ( person == null || role == null )
				continue;

			var object = { "person": person, "role": role, "note": notes };

			object[ 'song' ] = $( '#modal-song-id' ).val();
			console.log( object );
			ajaxRequest( '/api/song_person', 'POST', object, $( '#modal-song-person-success' ), $( '#modal-song-person-error' ), processSubmit );

		}
	}

	function processSubmit( data ) {
		$( '.modal-body-success' ).append( $( '<p>' ).text( "Song person with person " + data.person + ", role " + data.role +
			" and song " + data.song + " saved, id is " + data.id ) );
	}

} );