
$( document ).ready( function () {

	var $id = GetURLParameter( "id" );
	$( '#modal-session-id' ).val( $id );

	getSections();
	getSessionTypes();

	$( '#modal-session-location' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/location',
		onSelect: function ( suggestion ) {
			$( '#modal-session-location-id' ).val( suggestion.data );
		}
	} );
	$( '#modal-session-with' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/with',
		onSelect: function ( suggestion ) {
			$( '#modal-session-with-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-album' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/album',
		onSelect: function ( suggestion ) {
			$( '#modal-session-album-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-label' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/label',
		onSelect: function ( suggestion ) {
			$( '#modal-session-label-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-film' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/film',
		onSelect: function ( suggestion ) {
			$( '#modal-session-film-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-television' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/television',
		onSelect: function ( suggestion ) {
			$( '#modal-session-television-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-radio' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/radio',
		onSelect: function ( suggestion ) {
			$( '#modal-session-radio-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-session-other' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/other',
		onSelect: function ( suggestion ) {
			$( '#modal-session-other-id' ).val( suggestion.data );
		}
	} );

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/session/' + $id, function ( result ) {
			$( '#modal-session-section' ).val( result.section );
			$( '#modal-session-display-date' ).val( result.dateDisplay );

			var dateParse = new Date( result.date );
			var userTimezoneOffset = new Date().getTimezoneOffset() * 60000;
			var date = new Date( dateParse.getTime() - userTimezoneOffset ).toJSON().slice( 0, 16 );
			$( '#modal-session-date' ).val( date );

			$( '#modal-session-location-id' ).val( result.location.id );
			$( '#modal-session-location' ).val( result.location.name );

			if ( result.forAlbum != null ) {
				$( '#modal-session-album' ).val( result.forAlbum.title );
				$( '#modal-session-album-id' ).val( result.forAlbum.id );
				$( "input[type=radio][name=for][value=album]" ).attr( "checked", true );
				$( '#modal-session-div-album' ).removeClass( "hidden" );
			} else if ( result.film != null ) {
				$( '#modal-session-film' ).val( result.film.title );
				$( '#modal-session-film-id' ).val( result.film.id );
				$( "input[type=radio][name=for][value=film]" ).attr( "checked", true );
				$( '#modal-session-div-film' ).removeClass( "hidden" );
			} else if ( result.radio != null ) {
				$( '#modal-session-radio' ).val( result.radio.title );
				$( '#modal-session-radio-id' ).val( result.radio.id );
				$( "input[type=radio][name=for][value=radio]" ).attr( "checked", true );
				$( '#modal-session-div-radio' ).removeClass( "hidden" );
			} else if ( result.television != null ) {
				$( '#modal-session-television' ).val( result.television.title );
				$( '#modal-session-television-id' ).val( result.television.id );
				$( "input[type=radio][name=for][value=television]" ).attr( "checked", true );
				$( '#modal-session-div-television' ).removeClass( "hidden" );
			} else if ( result.label != null ) {
				$( '#modal-session-label' ).val( result.label.name );
				$( '#modal-session-label-id' ).val( result.label.id );
				$( "input[type=radio][name=for][value=label]" ).attr( "checked", true );
				$( '#modal-session-div-label' ).removeClass( "hidden" );
			} else if ( result.forOther != null ) {
				$( '#modal-session-other' ).val( result.forOther.description );
				$( '#modal-session-other-id' ).val( result.forOther.id );
				$( "input[type=radio][name=for][value=other]" ).attr( "checked", true );
				$( '#modal-session-div-other' ).removeClass( "hidden" );
			}

			$( '#modal-session-session-type' ).val( result.type );
			$( '#modal-session-notes' ).val( result.notes );
			$( '#modal-session-session-number' ).val( result.sessionNumber );
			$( '#modal-session-with' ).val( result.with.name );
			$( '#modal-session-with-id' ).val( result.with.id );
		} );
	}
	else {
		$( '#btn-session-songs' ).attr( 'disabled', true );
		$( '#btn-session-people' ).attr( 'disabled', true );
	}
	$( '#btn-section-edit' ).click( function () {
		$( "#div-modal-section" ).load( 'section.html', function () {
			$( '#modal-section-id' ).val( $( '#modal-session-section' ).val() );
			$.getScript( 'js/section.js' );
			$( '#section-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-section-new' ).click( function () {
		$( "#div-modal-section" ).load( 'section.html', function () {
			$.getScript( 'js/section.js' );
			$( '#section-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-location-edit' ).click( function () {
		$( "#div-modal-location" ).load( 'location.html', function () {
			$( '#modal-location-id' ).val( $( '#modal-session-location-id' ).val() );
			$.getScript( 'js/location.js' );
			$( '#location-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-location-new' ).click( function () {
		$( "#div-modal-location" ).load( 'location.html', function () {
			$.getScript( 'js/location.js' );
			$( '#location-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-label-edit' ).click( function () {
		$( "#div-modal-label" ).load( 'label.html', function () {
			$( '#modal-label-id' ).val( $( '#modal-session-label-id' ).val() );
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-label-new' ).click( function () {
		$( "#div-modal-label" ).load( 'label.html', function () {
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-album-edit' ).click( function () {
		$( "#div-modal-album" ).load( 'album.html', function () {
			$( '#modal-album-id' ).val( $( '#modal-session-album-id' ).val() );
			$.getScript( 'js/album.js' );
			$( '#album-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-album-new' ).click( function () {
		$( "#div-modal-album" ).load( 'album.html', function () {
			$.getScript( 'js/album.js' );
			$( '#album-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-other-edit' ).click( function () {
		$( "#div-modal-other" ).load( 'other.html', function () {
			$( '#modal-other-id' ).val( $( '#modal-session-other-id' ).val() );
			$.getScript( 'js/other.js' );
			$( '#other-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-other-new' ).click( function () {
		$( "#div-modal-other" ).load( 'other.html', function () {
			$.getScript( 'js/other.js' );
			$( '#other-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-session-type-edit' ).click( function () {
		$( "#div-modal-session-type" ).load( 'session_type.html', function () {
			$( '#modal-session-type-id' ).val( $( '#modal-session-session-type' ).val() );
			$.getScript( 'js/session_type.js' );
			$( '#session-type-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-session-type-new' ).click( function () {
		$( "#div-modal-session-type" ).load( 'session_type.html', function () {
			$.getScript( 'js/session_type.js' );
			$( '#session-type-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-film-edit' ).click( function () {
		$( "#div-modal-film" ).load( 'film.html', function () {
			$( '#modal-film-id' ).val( $( '#modal-session-film-id' ).val() );
			$.getScript( 'js/film.js' );
			$( '#film-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-film-new' ).click( function () {
		$( "#div-modal-film" ).load( 'film.html', function () {
			$.getScript( 'js/film.js' );
			$( '#film-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-radio-edit' ).click( function () {
		$( "#div-modal-radio" ).load( 'radio.html', function () {
			$( '#modal-radio-id' ).val( $( '#modal-session-radio-id' ).val() );
			$.getScript( 'js/radio.js' );
			$( '#radio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-radio-new' ).click( function () {
		$( "#div-modal-radio" ).load( 'radio.html', function () {
			$.getScript( 'js/radio.js' );
			$( '#radio-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-television-edit' ).click( function () {
		$( "#div-modal-television" ).load( 'television.html', function () {
			$( '#modal-television-id' ).val( $( '#modal-session-television-id' ).val() );
			$.getScript( 'js/television.js' );
			$( '#television-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-television-new' ).click( function () {
		$( "#div-modal-television" ).load( 'television.html', function () {
			$.getScript( 'js/television.js' );
			$( '#television-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-with-edit' ).click( function () {
		$( "#div-modal-with" ).load( 'with.html', function () {
			$( '#modal-with-id' ).val( $( '#modal-session-with-id' ).val() );
			$.getScript( 'js/with.js' );
			$( '#with-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-with-new' ).click( function () {
		$( "#div-modal-with" ).load( 'with.html', function () {
			$.getScript( 'js/with.js' );
			$( '#with-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-session-songs' ).click( function () {
		var id = $( '#modal-session-id' ).val();
		window.open( "/modals/session_song.html?id=" + id, "_blank" );
	} );
	$( '#btn-session-people' ).click( function () {
		var id = $( '#modal-session-id' ).val();
		window.open( "/modals/session_song_person.html?id=" + id, "_blank" );
	} );

	$( '#modal-session-submit' ).click( function () {
		var section = $( '#modal-session-section' ).val();
		var date_display = $( '#modal-session-display-date' ).val();

		var date = $( '#modal-session-date' ).val();

		var userTimezoneOffset = new Date().getTimezoneOffset() * 60000;
		date = new Date( new Date( date ).getTime() + userTimezoneOffset ).getTime();

		var location = $( '#modal-session-location-id' ).val();

		// We only want the highlighted one
		var label = null;
		var album = null;
		var other = null;
		var film = null;
		var radio = null;
		var television = null;

		var radioValue = $( "input[name='for']:checked" ).val();
		if ( radioValue == "label" )
			label = $( '#modal-session-label-id' ).val();
		else if ( radioValue == "album" )
			album = $( '#modal-session-album-id' ).val();
		else if ( radioValue == "other" )
			other = $( '#modal-session-other-id' ).val();
		else if ( radioValue == "film" )
			film = $( '#modal-session-film-id' ).val();
		else if ( radioValue == "television" )
			television = $( '#modal-session-television-id' ).val();
		else if ( radioValue == "radio" )
			radio = $( '#modal-session-radio-id' ).val();

		var type = $( '#modal-session-session-type' ).val();
		var notes = $( '#modal-session-notes' ).val();
		var session_number = $( '#modal-session-session-number' ).val();
		var display_artist = $( '#modal-session-with-id' ).val();

		var id = $( '#modal-session-id' ).val();

		var object = {
			"section": section, "dateDisplay": date_display, "date": date, "location": location, "label": label,
			"forAlbum": album, "forOther": other, "type": type, "notes": notes, "sessionNumber": session_number,
			"film": film, "with": display_artist, "radio": radio, "television": television
		};

		if ( id == "" || id == "undefined" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/session', 'POST', object, $( '#modal-session-success' ), $( '#modal-session-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/session/' + $id, 'PUT', object, $( '#modal-session-success' ), $( '#modal-session-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$id = data.id;
		$( '#modal-session-id' ).val( data.id );
		console.log("WORK BITHC");
		$( '#btn-session-songs' ).attr( 'disabled', false );
		$( '#btn-session-people' ).attr( 'disabled', false );
	}

	$( 'input[type="radio"]' ).on( 'change', function ( e ) {
		var target = e.target.value;

		// Hide everything
		$( '#modal-session-div-album' ).addClass( "hidden" );
		$( '#modal-session-div-label' ).addClass( "hidden" );
		$( '#modal-session-div-film' ).addClass( "hidden" );
		$( '#modal-session-div-television' ).addClass( "hidden" );
		$( '#modal-session-div-radio' ).addClass( "hidden" );
		$( '#modal-session-div-other' ).addClass( "hidden" );

		if ( target == "album" )
			$( '#modal-session-div-album' ).removeClass( "hidden" );

		if ( target == "label" )
			$( '#modal-session-div-label' ).removeClass( "hidden" );

		if ( target == "film" )
			$( '#modal-session-div-film' ).removeClass( "hidden" );

		if ( target == "other" )
			$( '#modal-session-div-other' ).removeClass( "hidden" );

		if ( target == "radio" )
			$( '#modal-session-div-radio' ).removeClass( "hidden" );

		if ( target == "television" )
			$( '#modal-session-div-television' ).removeClass( "hidden" );
	} );
} );

function getSections( providedVal ) {
	// Get a list of role_groups
	var select = $( '#modal-session-section' );
	var selectVal = select.val();

	select.find( 'option' ).remove();

	$.getJSON( '/api/section', function ( result ) {
		$.each( result, function ( id, item ) {
			select.append( '<option value="' + item.id + '">' + item.title + '</option>' );
		} );

		select.val( selectVal );

		if ( providedVal != null ) {
			select.val( providedVal );
		}
	} );
}

function getSessionTypes( providedVal ) {
	var select = $( '#modal-session-session-type' );
	var selectVal = select.val();

	select.find( 'option' ).remove();

	$.getJSON( '/api/session_type', function ( result ) {
		$.each( result, function ( id, item ) {
			select.append( '<option value="' + item.id + '">' + item.name + '</option>' );
		} );

		select.val( selectVal );

		if ( providedVal != null ) {
			select.val( providedVal );
		}
	} );
}