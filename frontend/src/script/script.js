let tarefas = [
    {
        id: 1,
        titulo: "Estudar JavaScript",
        descricao: "Revisar DOM e eventos",
        prazo: "2026-10-05",
        prioridade: "3",
        categoria: "Trabalho",
        status: "TODO"
    },
    {
        id: 2,
        titulo: "Fazer trabalho de Java",
        descricao: "Finalizar o backend da aplicação",
        prazo: "2026-10-08",
        prioridade: "5",
        categoria: "Estudos",
        status: "DOING"
    }

];

let tarefaEditando = null;
let filtroStatus = "";
let filtroPrioridade = "";
let filtroCategoria = "";
let tarefaParaExcluir = null;

const formTarefa = document.getElementById("form-tarefa");
const botaoAdicionar = document.getElementById("botao-adicionar");
const botaoCancelar = document.getElementById("botao-cancelar");
const modalElemento = document.getElementById("modalTarefa");
const modalTarefa = new bootstrap.Modal(modalElemento);
const modalConfirmarElemento = document.getElementById("modalConfirmarExclusao");
const modalConfirmarExclusao = new bootstrap.Modal(modalConfirmarElemento);
const botaoConfirmarExclusao = document.getElementById("botao-confirmar-exclusao");
const botaoAplicarFiltro = document.getElementById("botao-aplicar-filtro");
const botaoLimparFiltro = document.getElementById("botao-limpar-filtro");

botaoAdicionar.addEventListener("click", function() {
    tarefaEditando = null;
    formTarefa.reset();
    document.getElementById("modalTarefaLabel").textContent = "Nova tarefa";
});

botaoCancelar.addEventListener("click", function() {
    tarefaEditando = null;
    formTarefa.reset();
});

formTarefa.addEventListener("submit", function(event) {
    event.preventDefault();

    const titulo = document.getElementById("titulo").value;
    const descricao = document.getElementById("descricao").value;
    const prazo = document.getElementById("prazo").value;
    const prioridade = document.getElementById("prioridade").value;
    const categoria = document.getElementById("categoria").value;
    const status = document.getElementById("status").value;

    const hoje = new Date().toISOString().split("T")[0];

    if (prazo < hoje) {
        alert("A data de término não pode ser anterior à data atual.");
        return;
    }

    if (tarefaEditando === null) {
        const tarefa = {
            id: Date.now(),
            titulo: titulo,
            descricao: descricao,
            prazo: prazo,
            prioridade: prioridade,
            categoria: categoria,
            status: status
        };
        tarefas.push(tarefa);
    }

    else {
        const tarefa =
            tarefas.find(function(tarefa) {
                return tarefa.id === tarefaEditando;
            });

        tarefa.titulo = titulo;
        tarefa.descricao = descricao;
        tarefa.prazo = prazo;
        tarefa.prioridade = prioridade;
        tarefa.categoria = categoria
        tarefa.status = status;

        tarefaEditando = null;
    }

    formTarefa.reset();
    modalTarefa.hide();
    listarTarefas();
});

function listarTarefas() {
    const container = document.getElementById("tarefas");
    container.innerHTML = "";

    if (tarefas.length === 0) {
        container.innerHTML = `
            <div class="col-12">
                <div class="text-center py-5">
                    <h3 class="card-title">Nenhuma tarefa cadastrada</h3>
                    <p class="texto-secundario">
                        Adicione uma nova tarefa para começar.
                    </p>
                </div>
            </div>
        `;

        atualizarResumo();
        return;
    }

    const tarefasFiltradas = tarefas.filter(function(tarefa) {
        if (filtroStatus !== "" && tarefa.status !== filtroStatus) {
            return false;
        }

        if (filtroPrioridade !== "" && tarefa.prioridade !== filtroPrioridade) {
            return false;
        }

        if (filtroCategoria !== "" && tarefa.categoria !== filtroCategoria) {
            return false;
        }
        return true;
    });

    if (tarefasFiltradas.length === 0) {
        container.innerHTML = `
            <div class="col-12">
                <div class="text-center py-5">
                    <h3 class="card-title">
                        Nenhuma tarefa encontrada
                    </h3>
                    <p class="texto-secundario">
                        Não existem tarefas que correspondam aos filtros selecionados.
                    </p>
                </div>
            </div>
        `;

        atualizarResumo();
        return;
    }

    tarefasFiltradas.forEach(function(tarefa) {
        const statusClass = tarefa.status.toLowerCase();
        const prioridadeClass = tarefa.prioridade.toLowerCase();
        const elemento = document.createElement("div");

        elemento.className = "col-12 col-md-6 col-xl-4";
        elemento.innerHTML = `
            <div class="card card-tarefa">
                <div class="card-body d-flex flex-column">
                    <div class="d-flex flex-wrap gap-2 mb-3">
                        <span
                            class="
                                badge-tarefa
                                status-${statusClass}
                            "
                        >
                            ${tarefa.status}
                        </span>
                        <span
                            class="
                                badge-tarefa
                                prioridade-${prioridadeClass}
                            "
                        >
                            ${tarefa.prioridade}
                        </span>
                        <span class="badge-tarefa">
                            ${tarefa.categoria}
                        </span>
                    </div>
                    <h3 class="card-title">${tarefa.titulo}</h3>
                    <p class="card-text">${tarefa.descricao}</p>
                    <p class="card-text mb-3">Prazo: ${tarefa.prazo}</p>
                    <div class="mt-auto">
                        <button
                            class="btn btn-sm btn-editar me-1"
                            onclick="editarTarefa(${tarefa.id})"
                        >
                            Editar
                        </button>
                        <button
                            class="btn btn-sm btn-excluir"
                            onclick="excluirTarefa(${tarefa.id})"
                        >
                            Excluir
                        </button>
                    </div>
                </div>
            </div>
        `;
        container.appendChild(elemento);
    });
    atualizarResumo();
}

function atualizarResumo() {
    const total = tarefas.length;
    const todo = tarefas.filter(function(tarefa) {
            return tarefa.status === "TODO";
        }).length;

    const doing = tarefas.filter(function(tarefa) {
            return tarefa.status === "DOING";
        }).length;

    const done = tarefas.filter(function(tarefa) {
            return tarefa.status === "DONE";
        }).length;

    document.getElementById("total-tarefas").textContent = total;
    document.getElementById("total-todo").textContent = todo;
    document.getElementById("total-doing").textContent = doing;
    document.getElementById("total-done").textContent = done;
}

function excluirTarefa(id) {
    tarefaParaExcluir = id;
    modalConfirmarExclusao.show();
}

botaoConfirmarExclusao.addEventListener(
    "click",
    function() {
        tarefas =
            tarefas.filter(function(tarefa) {
                return tarefa.id !== tarefaParaExcluir;
            });

        tarefaParaExcluir = null;
        modalConfirmarExclusao.hide();
        listarTarefas();
    }
);

function editarTarefa(id) {
    const tarefa =
        tarefas.find(function(tarefa) {
            return tarefa.id === id;
        });

    tarefaEditando = id;

    document.getElementById("titulo").value = tarefa.titulo;
    document.getElementById("descricao").value = tarefa.descricao;
    document.getElementById("prazo").value = tarefa.prazo;
    document.getElementById("prioridade").value = tarefa.prioridade;
    document.getElementById("categoria").value = tarefa.categoria;
    document.getElementById("status").value = tarefa.status;
    document.getElementById("modalTarefaLabel").textContent = "Editar tarefa";
    modalTarefa.show();
}

modalElemento.addEventListener(
    "hidden.bs.modal",
    function() {
        formTarefa.reset();
        tarefaEditando = null;
        document.getElementById("modalTarefaLabel").textContent = "Nova tarefa";
    }
);

botaoAplicarFiltro.addEventListener("click", function() {
    filtroStatus = document.getElementById("filtro-status").value;
    filtroPrioridade = document.getElementById("filtro-prioridade").value;
    filtroCategoria = document.getElementById("filtro-categoria").value;

    listarTarefas();

    bootstrap.Modal.getInstance(document.getElementById("modalFiltro")).hide();
});

botaoLimparFiltro.addEventListener("click", function() {
    filtroStatus = "";
    filtroPrioridade = "";
    filtroCategoria = "";

    document.getElementById("filtro-status").value = "";
    document.getElementById("filtro-prioridade").value = "";
    document.getElementById("filtro-categoria").value = "";

    listarTarefas();
});

listarTarefas();