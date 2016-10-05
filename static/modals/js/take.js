
$( document ).ready( function () {

	var $id = $( '#modal-take-id' ).val();
	var $callerId = $( '#modal-take-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/take/' + $id, function ( result ) {
			$( '#modal-take-title' ).val( result.title );
			if ( result.originalRelease != null ) {
				$( '#modal-take-original-release' ).val( result.originalRelease.title );
				$( '#modal-take-original-release-id' ).val( result.originalRelease.id );
			}
			if ( result.keyRelease != null ) {
				$( '#modal-take-key-release' ).val( result.keyRelease.title );
				$( '#modal-take-key-release-id' ).val( result.keyRelease.id );
			}
			if ( result.film != null ) {
				$( '#modal-take-film' ).val( result.film.title );
				$( '#modal-take-film-id' ).val( result.film.id );
			}
			if ( result.radio != null ) {
				$( '#modal-take-radio' ).val( result.radio.title );
				$( '#modal-take-radio-id' ).val( result.radio.id );
			}
			if ( result.television != null ) {
				$( '#modal-take-television' ).val( result.television.title );
				$( '#modal-take-television-id' ).val( result.television.id );
			}
			$( '#modal-take-length' ).val( result.length );
			$( '#modal-take-notes' ).val( result.notes );
			$( '#modal-take-position' ).val( result.position );
		} );
	}

	$( '#btn-take-key-release-edit' ).click( function () {
		$( "#div-modal-release" ).load( 'release.html', function () {
			$( '#modal-release-id' ).val( $( '#modal-take-key-release-id' ).val() );
			$.getScript( 'js/release.js' );
			$( '#release-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-take-key-release-new' ).click( function () {
		$( "#div-modal-release" ).load( 'release.html', function () {
			$.getScript( 'js/release.js' );
			$( '#release-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-take-original-release-edit' ).click( function () {
		$( "#div-modal-release" ).load( 'release.html', function () {
			$( '#modal-release-id' ).val( $( '#modal-take-original-release-id' ).val() );
			$( '#modal-release-caller' ).val( "original" );
			$.getScript( 'js/release.js' );
			$( '#release-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-take-original-release-new' ).click( function () {
		$( "#div-modal-release" ).load( 'release.html', function () {
			$( '#modal-release-caller' ).val( "original" );
			$.getScript( 'js/release.js' );
			$( '#release-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-take-submit' ).click( function () {

		var title = $( '#modal-take-title' ).val();
		var key_release = $( '#modal-take-key-release-id' ).val();
		var original_release = $( '#modal-take-original-release-id' ).val();
		var film = $( '#modal-take-film-id' ).val();
		var television = $( '#modal-take-television-id' ).val();
		var radio = $( '#modal-take-radio-id' ).val();
		var length = $( '#modal-take-length' ).val();
		var notes = $( '#modal-take-notes' ).val();
		var position = $( '#modal-take-position' ).val();
		var session_song = $( '#modal-take-session-song-id' ).val();

		var id = $( '#modal-take-id' ).val();

		var object = {
			"title": title,
			"keyRelease": key_release,
			"originalRelease": original_release,
			"length": length,
			"notes": notes,
			"position": position,
			"sessionSong": session_song,
			"film": film,
			"television": television,
			"radio": radio
		};

		console.log( object );
		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/take', 'POST', object, $( '#modal-take-success' ), $( '#modal-take-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/take/' + $id, 'PUT', object, $( '#modal-take-success' ), $( '#modal-take-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '#modal-session-song-take-id-' + $callerId ).val( data.id );
		$( '#modal-session-song-take-title-' + $callerId ).val( data.title );
		$( "#modal-take-cancel" ).trigger( "click" );
	}

	$( '#modal-take-key-release' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/release',
		onSelect: function ( suggestion ) {
			$( '#modal-take-key-release-id' ).val( suggestion.data );
		}
	} );
	$( '#modal-take-original-release' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/release',
		onSelect: function ( suggestion ) {
			$( '#modal-take-original-release-id' ).val( suggestion.data );
		}
	} );

	$( '#btn-film-edit' ).click( function () {
		$( "#div-modal-film" ).load( 'film.html', function () {
			$( '#modal-film-id' ).val( $( '#modal-take-film-id' ).val() );
			$( '#modal-film-caller' ).val( "take" );
			$.getScript( 'js/film.js' );
			$( '#film-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-film-new' ).click( function () {
		$( "#div-modal-film" ).load( 'film.html', function () {
			$( '#modal-film-caller' ).val( "take" );
			$.getScript( 'js/film.js' );
			$( '#film-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-radio-edit' ).click( function () {
		$( "#div-modal-radio" ).load( 'radio.html', function () {
			$( '#modal-radio-id' ).val( $( '#modal-take-radio-id' ).val() );
			$( '#modal-radio-caller' ).val( "take" );
			$.getScript( 'js/radio.js' );
			$( '#radio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-radio-new' ).click( function () {
		$( "#div-modal-radio" ).load( 'radio.html', function () {
			$( '#modal-radio-caller' ).val( "take" );
			$.getScript( 'js/radio.js' );
			$( '#radio-modal' ).modal( 'show' );
		} );
	} );

	$( '#btn-television-edit' ).click( function () {
		$( "#div-modal-television" ).load( 'television.html', function () {
			$( '#modal-television-id' ).val( $( '#modal-take-television-id' ).val() );
			$( '#modal-television-caller' ).val( "take" );
			$.getScript( 'js/television.js' );
			$( '#television-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-television-new' ).click( function () {
		$( "#div-modal-television" ).load( 'television.html', function () {
			$( '#modal-television-caller' ).val( "take" );
			$.getScript( 'js/television.js' );
			$( '#television-modal' ).modal( 'show' );
		} );
	} );


	$( '#modal-take-film' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/film',
		onSelect: function ( suggestion ) {
			$( '#modal-take-film-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-take-television' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/television',
		onSelect: function ( suggestion ) {
			$( '#modal-take-television-id' ).val( suggestion.data );
		}
	} );

	$( '#modal-take-radio' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/radio',
		onSelect: function ( suggestion ) {
			$( '#modal-take-radio-id' ).val( suggestion.data );
		}
	} );


	$("#modal-take-original-release").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-take-original-release-id").val("");
		}
	});


	$("#modal-take-key-release").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-take-key-release-id").val("");
		}
	});


	$("#modal-take-film").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-take-film-id").val("");
		}
	});


	$("#modal-take-radio").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-take-radio-id").val("");
		}
	});


	$("#modal-take-television").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-take-television-id").val("");
		}
	});
} );

