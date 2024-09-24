public class API {
    public Cena carregarCena(int id) {
        switch (id) {
            case 1: // Cena 1
                return new Cena(
                        "Dia 4 de Agosto de 2001. Olá, me chamo Henri e este é meu diário. " +
                                "Acordei em um dia muito estranho, com um clima vazio e sombrio. " +
                                "Devo olhar a JANELA ou procurar meus PAIS?",
                        new String[] {"Olhar a janela", "Procurar meus pais"},
                        new int[] {2, 3}  // Escolha 1 vai para Cena 2, Escolha 2 vai para Cena 3
                );
            case 2: // Cena alternativa JANELA
                return new Cena(
                        "Abri a cortina, mas só vi um vazio imenso, um clima muito gelado e uma neblina obscura. " +
                                "Agora vou procurar meus PAIS para saber o que está acontecendo.",
                        new String[] {"Procurar meus pais"},
                        new int[] {3}
                );
            case 3: // Cena 2
                return new Cena(
                        "Abri a porta e fui em direção ao quarto dos meus pais. " +
                                "A casa está muito escura. Devo procurar uma LANTERNA ou continuar no escuro?",
                        new String[] {"Procurar lanterna", "Continuar no escuro"},
                        new int[] {20, 5}  // Escolha 1 vai para Cena 4, Escolha 2 vai para Cena 5
                );
            case 20: // Cena 3
                return new Cena(
                        "Que maravilha pelo menos algo deu certo, achei uma lanterna !" +
                                "Agora preciso USALA !",

                        new String[] {"Usar lanterna"},
                        new int[] {4}  // Escolha 1 vai para Cena 6, Escolha 2 vai para Cena 7, Escolha 3 vai para Cena 8
                );

            case 4: // Cena 3
                return new Cena(
                        "Estava muito escuro, agora com a luz da lanterna consigo ver alguma coisa" +
                                "vou procurar meus PAIS, mas não há ninguém no quarto. " +
                                "Devo procurar meu CELULAR ou continuar procurando pela casa?",
                        new String[] {"Procurar celular", "Procurar pela casa",},
                        new int[] {6, 7, 5}  // Escolha 1 vai para Cena 6, Escolha 2 vai para Cena 7, Escolha 3 vai para Cena 8
                );
            case 5: // Cena alternativa CONTINUAR NO ESCURO
                return new Cena(
                        "Você tentou continuar no escuro, mas acabou se perdendo e ficou muito confuso. " +
                                "Talvez seja melhor procurar uma lanterna.",
                        new String[] {"Voltar para procurar uma lanterna"},
                        new int[] {3}
                );
            case 6: // Cena 4 - CELULAR
                return new Cena(
                        "Peguei meu celular, mas está sem sinal. Vou ter que continuar procurando pela casa.",
                        new String[] {"Procurar pela casa"},
                        new int[] {7}
                );
            case 7: // Cena 5 - Procurar os pais
                return new Cena(
                        "Ao abrir a porta do corredor, encontrei meus pais sentados no sofá. " +
                                "A TV exibe uma imagem toda chuviscada e o cheiro de sangue está no ar. " +
                                "Devo CONVERSAR com eles ou ignorar meu medo?",
                        new String[] {"Conversar com eles"},
                        new int[] {8}
                );
            case 8: // Cena 6
                return new Cena(
                        "Cheguei perto e o cheiro de sangue aumentou. " +
                                "Meus pais estavam mortos, com os órgãos expostos. " +
                                "Corri desesperadamente para meu quarto para pensar no que fazer.",
                        new String[] {"Correr para o quarto"},
                        new int[] {9}
                );
            case 9: // Cena 7
                return new Cena(
                        "Liguei o computador e descobri que o mundo está em um apocalipse zumbi. " +
                                "Sem sinal no celular, talvez eu deva usar o TELEFONE-FIXO da casa.",
                        new String[] {"Usar telefone-fixo"},
                        new int[] {10}
                );
            case 10: // Cena 8 - Escolha entre vida e morte
                return new Cena(
                        "Após ligar para a polícia, eles dizem para correr para a delegacia. " +
                                "Devo seguir o conselho e correr para a DELEGACIA, ou investigar o barulho na sala?",
                        new String[] {"Delegacia", "Investigar o barulho"},
                        new int[] {11, 12}  // Escolha 1 vai para Vida (Cena 11), Escolha 2 vai para Morte (Cena 12)
                );
            case 11: // Cena de Vida
                return new Cena(
                        "Você correu para a delegacia e conseguiu se salvar. O mundo está em caos, " +
                                "mas pelo menos você está seguro por enquanto. Fim de Jogo - Você sobreviveu!",
                        new String[] {},
                        new int[] {}
                );
            case 12: // Cena de Morte
                return new Cena(
                        "Você decidiu investigar o barulho e encontrou seus pais transformados em zumbis. " +
                                "Antes de poder reagir, eles te atacam. Fim de Jogo - Você morreu!",
                        new String[] {},
                        new int[] {}
                );
            default:
                return null;
        }
    }
}
