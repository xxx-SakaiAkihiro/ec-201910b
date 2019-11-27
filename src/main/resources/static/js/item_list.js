/**
 * 
 */

$( function() {
  var data = [ [(${ItemListForAutocomplete})] ];


$("#code").autocomplete({
    source: data,
    autoFocus: true,
    delay: 500,
    minLength: 1
  });
});