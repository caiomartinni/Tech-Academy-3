<?php
echo"teste";
$conteudo = file_get_contents ("http://localhost/Tech-Academy-3/");

$arrayAssociativo = json_decode ($conteudo);

include "template.phtml";

?>