
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Diario de um Apocalipse</title>
    <link rel="shortcut icon" href="img/Design sem nome (32).png">
</head>
<body>
    <div class="container">
        <div class="game-area">
            <h1>Diario de um Apocalipse</h1>
            <div class="output">
                <?php
                session_start();

                // Conexão com o banco de dados
                $servername = "localhost";
                $username = "root"; // Seu usuário
                $password = ""; // Sua senha
                $dbname = "techacademy3"; // Nome do banco de dados
                
                $conn = new mysqli($servername, $username, $password, $dbname);
                
                // Verifica a conexão
                if ($conn->connect_error) {
                    die("Erro de conexão: " . $conn->connect_error);
                }
                
                function carregarCena($id, $conn) {
                    $sql = "SELECT * FROM cenas WHERE id = ?";
                    $stmt = $conn->prepare($sql);
                    $stmt->bind_param("i", $id);
                    $stmt->execute();
                    $resultado = $stmt->get_result();
                    return $resultado->fetch_assoc();
                }
                
                function adicionarItemInventario($item) {
                    if (!isset($_SESSION['inventario'])) {
                        $_SESSION['inventario'] = [];
                    }
                    if (!in_array($item, $_SESSION['inventario'])) {
                        $_SESSION['inventario'][] = $item;
                        echo "$item foi adicionado ao seu inventário.<br>";
                    } else {
                        echo "Você já tem $item no seu inventário.<br>";
                    }
                }
                
                // Inicia o jogo
                if ($_SERVER['REQUEST_METHOD'] == 'POST') {
                    $input = trim($_POST['entrada']);
                    
                    if (!isset($_SESSION['cenaAtual'])) {
                        // Primeira execução
                        if (strtolower($input) == 'start') {
                            $_SESSION['cenaAtual'] = 1;
                            $cena = carregarCena($_SESSION['cenaAtual'], $conn);
                            echo "<p>" . $cena['descricao'] . "</p>";
                            $_SESSION['opcoes'] = explode(',', $cena['opcoes']);
                        } elseif (strtolower($input) == 'carregar' && isset($_SESSION['progresso'])) {
                            $_SESSION['cenaAtual'] = $_SESSION['progresso']['cenaAtual'];
                            echo "Progresso carregado com sucesso!<br>";
                            $cena = carregarCena($_SESSION['cenaAtual'], $conn);
                            echo "<p>" . $cena['descricao'] . "</p>";
                            $_SESSION['opcoes'] = explode(',', $cena['opcoes']);
                        } elseif (strtolower($input) == 'sair') {
                            session_destroy();
                            echo "Saindo do jogo...";
                        } else {
                            echo "Comando inválido. Digite 'Start' para começar ou 'Carregar' para carregar um jogo salvo.<br>";
                        }
                    } else {
                        // Jogando
                        if ($input == 's') {
                            $_SESSION['progresso'] = ['cenaAtual' => $_SESSION['cenaAtual'], 'inventario' => $_SESSION['inventario']];
                            echo "Progresso salvo com sucesso!<br>";
                        } elseif ($input == 'r') {
                            $_SESSION['cenaAtual'] = 1; // Reinicia o jogo
                            $_SESSION['inventario'] = [];
                            echo "Jogo reiniciado.<br>";
                            $cena = carregarCena($_SESSION['cenaAtual'], $conn);
                            echo "<p>" . $cena['descricao'] . "</p>";
                            $_SESSION['opcoes'] = explode(',', $cena['opcoes']);
                        } elseif ($input == 'x') {
                            session_destroy();
                            echo "Saindo do jogo...";
                        } elseif ($input == 'i') {
                            echo "Itens no seu inventário: " . implode(', ', $_SESSION['inventario']) . "<br>";
                        } else {
                            $opcoes = $_SESSION['opcoes'];
                            if (is_numeric($input) && $input >= 1 && $input <= count($opcoes)) {
                                $cenaAtual = $_SESSION['cenaAtual'];
                                $cenaProximaId = $cenaAtual + $input; // Ajuste conforme a lógica de seu jogo
                                $_SESSION['cenaAtual'] = $cenaProximaId;
                                $cena = carregarCena($cenaProximaId, $conn);
                                echo "<p>" . $cena['descricao'] . "</p>";
                
                                // Adiciona itens ao inventário conforme a cena
                                if ($cena['id'] == 20) {
                                    adicionarItemInventario("Lanterna");
                                } elseif ($cena['id'] == 6) {
                                    adicionarItemInventario("Celular");
                                }
                
                                // Exibe as opções numéricas
                                $opcoes = explode(',', $cena['opcoes']);
                                echo "<h3>Opções:</h3>";
                                foreach ($opcoes as $key => $opcao) {
                                    echo ($key + 1) . ": " . $opcao . "<br>";
                                }
                            } else {
                                echo "Escolha inválida. Tente novamente.<br>";
                            }
                        }
                    }
                } else {
                    echo "<p>Bem-vindo ao jogo! Digite 'Start' para começar, 'Carregar' para carregar um jogo salvo, ou 'Sair' para encerrar.</p>";
                }
                
                // Fecha a conexão
                $conn->close();
                ?>
            </div>
            <form method="post">
                <label for="entrada">Digite sua opção:</label>
                <input type="text" id="entrada" name="entrada" placeholder="Digite aqui..." required>
                <button type="submit">Enviar</button>
            </form>
            <div class="comandos">
                <h2>Comandos:</h2>
                <p>S: Salvar Jogo</p>
                <p>R: Reiniciar Jogo</p>
                <p>X: Sair do Jogo</p>
                <p>I: Checar Inventário</p>
            </div>
        </div>
    </div>
</body>

</html>
