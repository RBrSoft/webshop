- Pfad
- Query Parameter: ?
- Http Verb: GET, POST, PUT, DELETE (HEAD, OPTIONS, PATCH, TRACE)
- Response Body

REST: Ressourcen
Produkte
Kunden
Bestellungen


Lade alle Produkte vom Server:
GET /api/products

Lade Produkt über ID
GET /api/products/{id}

Lade Produkte über TAG
GET /api/products?tag={tag}

Erzeuge neues Produkt
POST /api/products

Lösche Produkt
DELETE /api/products/{id}

Update Produkt
PUT /api/products/{id}

Füge Tags zu Produkt hinzu
PUT /api/products/{id}/tags


Bestelle Produkt --> Erzeuge neue Bestellung
POST /api/orders

Füge Produkt zu einer Bestellung hinzu
PUT /api/orders/{id}/products
