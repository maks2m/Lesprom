
/*
* https://webdevkin.ru/posts/frontend/tabulator
* */


$(document).ready(function () {

});

var table = new Tabulator('#example-table', {
    data: tableData(),
    layout: 'fitColumns',
    columns: [
        {title: 'Статья', field: 'nameWorkplace', width: 550},
        {title: 'Количество голосов', field: 'countRates', align: 'left'},
        {title: 'Средняя оценка', field: 'rating'}
    ]
});

function tableData() {
    return [
        {
            nameWorkplace: 'заготовительный',
            employees: [
                {
                    id: '1',
                    fullName: 'Иванов'
                },
                {
                    id: '2',
                    fullName: 'Петров'
                }
            ]
        },
        {
            nameWorkplace: 'фрезерный',
            employees: [
                {
                    id: '3',
                    fullName: 'Сидоров'
                },
                {
                    id: '4',
                    fullName: 'Кокоберидзе'
                }
            ]
        }
    ];
}