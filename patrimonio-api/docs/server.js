const express = require('express');
const app = express();

app.get('/*', function(req, res) {
  res.sendFile(__dirname + '/teste-get-itens.html');
});

app.listen(4200, function() {
  console.log("Servidor rodando em http://localhost:4200")
});
