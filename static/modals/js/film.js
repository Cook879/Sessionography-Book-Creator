
$( document ).ready( function () {

	var $id = $( '#modal-film-id' ).val();
	var $caller = $( '#modal-film-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/film/' + $id, function ( result ) {
			$( '#modal-film-title' ).val( result.title );
			$( '#modal-film-studio' ).val( result.studio.name );
			$( '#modal-film-studio-id' ).val( result.studio.id );
			$( '#modal-film-year' ).val( result.year );
		} );
	}

	$( '#btn-studio-edit' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-id' ).val( $( '#modal-film-studio-id' ).val() );
			$( '#modal-studio-caller' ).val( "film" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-studio-new' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-caller' ).val( "film" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-film-submit' ).click( function () {
		var title = $( '#modal-film-title' ).val();
		var studio = $( '#modal-film-studio-id' ).val();
		var year = $( '#modal-film-year' ).val();

		var id = $( '#modal-film-id' ).val();

		var object = { "title": title, "studio": studio, "year": year };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/film', 'POST', object, $( '#modal-film-success' ), $( '#modal-film-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/film/' + $id, 'PUT', object, $( '#modal-film-success' ), $( '#modal-film-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "take" ) {
			$( '#modal-take-film-id' ).val( data.id );
			$( '#modal-take-film' ).val( data.title );
		} else {
			$( '#modal-session-film-id' ).val( data.id );
			$( '#modal-session-film' ).val( data.title );
		}
		$( "#modal-film-cancel" ).trigger( "click" );
	}

	$( '#modal-film-studio' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/studio',
		onSelect: function ( suggestion ) {
			$( '#modal-film-studio-id' ).val( suggestion.data );
		}
	} );
} );