package com.example.lesprom.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "technological_process")
@Getter
@Setter
@NoArgsConstructor
public class TechnologicalProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_start_work")
    private Date timeStartWork;

    @Column(name = "time_finish_work")
    private Date timeFinishWork;

    @Column(name = "operation_code")
    private Integer operationCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_employee", foreignKey = @ForeignKey(name = "technological_process_employee_fk"))
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "technological_process_order_fk"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_workplace", foreignKey = @ForeignKey(name = "technological_process_workplace_fk"))
    private Workplace workplace;

}
