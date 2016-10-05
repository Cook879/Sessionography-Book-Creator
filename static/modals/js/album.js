
$( document ).ready( function () {

	var $id = $( '#modal-album-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/album/' + $id, function ( result ) {
			$( '#modal-album-title' ).val( result.title );
			$( '#modal-album-label-id' ).val( result.label.id );
			$( '#modal-album-label' ).val( result.label.name );
			$( '#modal-album-year' ).val( result.year );
		} );
	}

	$( '#btn-album-label-edit' ).click( function () {
		$( "#div-modal-label-album" ).load( 'label.html', function () {
			$( '#modal-label-id' ).val( $( '#modal-album-label-id' ).val() );
			$( '#modal-label-caller' ).val( "album" );
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-album-label-new' ).click( function () {
		$( "#div-modal-label-album" ).load( 'label.html', function () {
			$( '#modal-label-caller' ).val( "album" );
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-album-submit' ).click( function () {
		var title = $( '#modal-album-title' ).val();
		var label = $( '#modal-album-label-id' ).val();
		var year = $( '#modal-album-year' ).val();

		var id = $( '#modal-album-id' ).val();

		var object = { "title": title, "label": label, "year": year };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/album', 'POST', object, $( '#modal-album-success' ), $( '#modal-album-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/album/' + $id, 'PUT', object, $( '#modal-album-success' ), $( '#modal-album-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '#modal-session-album-id' ).val( data.id );
		$( '#modal-session-album' ).val( data.title );
		$( "#modal-album-cancel" ).trigger( "click" );
	}

	$( '#modal-album-label' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/label',
		onSelect: function ( suggestion ) {
			$( '#modal-album-label-id' ).val( suggestion.data );
		}
	} );
} );