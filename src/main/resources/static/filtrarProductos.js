function filtrarProductos() {
    const precioMin = document.getElementById('precioMin').value || null;
    const precioMax = document.getElementById('precioMax').value || null;
    const categoria = document.getElementById('categoria').value || null;

    // Realizar una petición a la API para obtener productos filtrados
    fetch(`/productos?precioMin=${precioMin}&precioMax=${precioMax}&categoria=${categoria}`)
        .then(response => response.json())
        .then(data => {
            // Aquí deberías actualizar la vista de productos con los datos filtrados
            console.log(data); // Muestra los productos filtrados en consola
        })
        .catch(error => console.error('Error al filtrar productos:', error));
}
