// WebContent/js/confirmacao.js
function confirmarExclusao(event, url, nomeEPI) {
    event.preventDefault();
    
    // Verifica se SweetAlert2 está disponível
    if (typeof Swal !== 'undefined') {
        Swal.fire({
            title: 'Excluir EPI',
            html: `Tem certeza que deseja excluir o EPI <b>"${nomeEPI}"</b>?<br>Esta ação não pode ser desfeita.`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#3085d6',
            confirmButtonText: 'Sim, excluir!',
            cancelButtonText: 'Cancelar',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = url;
            }
        });
    } else {
        // Fallback para confirm nativo se SweetAlert2 não carregar
        const confirmacao = confirm(`Tem certeza que deseja excluir o EPI "${nomeEPI}"?`);
        if (confirmacao) {
            window.location.href = url;
        }
    }
    
    return false;
}