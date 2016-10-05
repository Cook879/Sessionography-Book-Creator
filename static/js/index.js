
$( document ).ready( function () {
	$( '#btn-session-edit' ).click( function () {
		var id = $( '#index-session-id' ).val();
		window.open( "/modals/session.html?id=" + id, "_blank" );
	} );
	$( '#btn-session-new' ).click( function () {
		window.open( "/modals/session.html", "_blank" );
	} );
	$( '#btn-book-string' ).click( function () {
		window.open( "/modals/book_string.html", "_blank" );
	} );

	$( '#btn-book-create' ).click( function () {
		$( '#btn-book-create' ).attr( 'disabled', true );
		$( '#index-success' ).text( "Creating books - be patient - this may take a few minutes!" );
		$.ajax( {
			url: '/api/book_generator',
			type: 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			success: function ( result, status, data ) {
				handleResponse( result.status, result.responseText, $( '#index-success' ), $( '#index-error' ) );
				$( '#btn-book-create' ).attr( 'disabled', false );
			},
			error: function ( result, status, xhr ) {
				handleResponse( result.status, result.responseText, $( '#index-success' ), $( '#index-error' ) );
				$( '#btn-book-create' ).attr( 'disabled', false );
			}
		} );
	} );

	$( '#index-session' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/session',
		onSelect: function ( suggestion ) {
			$( '#index-session-id' ).val( suggestion.data );
		}
	} );

} );

function handleResponse( status, message, success, error ) {
	if ( status == 200 || status == 201 ) {
		success.text( message );
		error.text( "" );
	} else {
		success.text( "" );
		error.text( message );
	}
}

function ajaxRequest( url, type, object, success, error, after ) {
	$.ajax( {
		url: url,
		type: type,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		data: JSON.stringify( object ),
		success: function ( result, status, data ) {
			handleResponse( result.status, result.responseText, success, error );
			after( data.responseJSON );
		},
		error: function ( result, status, xhr ) {
			handleResponse( result.status, result.responseText, success, error );
		}
	} );
}


// Source: http://www.jquerybyexample.net/2012/06/get-url-parameters-using-jquery.html
function GetURLParameter( sParam ) {
	var sPageURL = window.location.search.substring( 1 );
	var sURLVariables = sPageURL.split( '&' );
	for ( var i = 0; i < sURLVariables.length; i++ ) {
		var sParameterName = sURLVariables[ i ].split( '=' );
		if ( sParameterName[ 0 ] == sParam ) {
			return sParameterName[ 1 ];
		}
	}
}