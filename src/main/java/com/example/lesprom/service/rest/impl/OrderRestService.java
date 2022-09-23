package com.example.lesprom.service.rest.impl;

import com.example.lesprom.entity.Baguette;
import com.example.lesprom.entity.Cutter;
import com.example.lesprom.entity.Order;
import com.example.lesprom.entity.TechnologicalProcess;
import com.example.lesprom.exception.NotFoundException;
import com.example.lesprom.repo.BaguetteRepo;
import com.example.lesprom.repo.CutterRepo;
import com.example.lesprom.repo.OrderRepo;
import com.example.lesprom.repo.TechnologicalProcessRepo;
import com.example.lesprom.service.rest.AbstractRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderRestService extends AbstractRestService<Order, OrderRepo> {

    private final BaguetteRepo baguetteRepo;
    private final CutterRepo cutterRepo;
    private final TechnologicalProcessRepo technologicalProcessRepo;

    public OrderRestService(OrderRepo repository, BaguetteRepo baguetteRepo, CutterRepo cutterRepo, TechnologicalProcessRepo technologicalProcessRepo) {
        super(repository);
        this.baguetteRepo = baguetteRepo;
        this.cutterRepo = cutterRepo;
        this.technologicalProcessRepo = technologicalProcessRepo;
    }

    // метод отдает список заказов, отфильтрованных по конкретному участку
    // перенести логику сортировки и фильтрации метода в репозиторий (SQL запрос)
    public Page<Order> listOrdersOfWorkplace(Long idWorkplace) {

        /*
        // из фронта
        return this.orders.filter(o => {

        // ***** вывести в отдельную функцию ******
        const sortTP = o.technologicalProcesses.sort((firstTP, secondTp) => firstTP.operationCode - secondTp.operationCode);
        const firstSortedTP = sortTP.find(tp => tp.timeStartWork === null || tp.timeFinishWork === null);
        // ****************************************

        return o.technologicalProcesses.some(t => {
          return t.workplace.id === this.selectedWorkplace.id &&
              !this.isFinishWork(t) &&
              firstSortedTP.id === t.id;
        })
        })
        */


        //List<Order> listOrdersOnWorkplace = repository.findAllOnWorkplace(idWorkplace);
        List<Order> listOrdersOnWorkplace = repository.findAllByOrderById();

        List<Order> orderList = listOrdersOnWorkplace.stream().filter(o -> {
            List<TechnologicalProcess> sortedTP = o.getTechnologicalProcesses().stream()
                    .sorted(Comparator.comparing(TechnologicalProcess::getOperationCode))
                    .collect(Collectors.toList());
            TechnologicalProcess firstSortedTP = sortedTP.stream()
                    .filter(t -> t.getTimeStartWork() == null || t.getTimeFinishWork() == null)
                    .findFirst().orElse(null);

            return o.getTechnologicalProcesses().stream().anyMatch(t -> Objects.equals(firstSortedTP, t) && t.getWorkplace().getId() == idWorkplace);
        }).collect(Collectors.toList());
        System.out.println("sdfg");
        return new PageImpl<>(orderList);
    }

    @Override
    public Page<Order> list(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo <= -1) {
            Sort sortItem = Sort.by(sortBy);
            return new PageImpl<>(repository.findAll(sortItem));
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            return repository.findAll(paging);
        }
    }

    @Override
    public Order getById(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Order create(Order item) {
        item.setId(null);
        setChildren(item);
        return repository.save(item);
    }

    @Override
    public Order update(Long id, Order item) {
        Order itemFromDB = repository.findById(id).orElseThrow(NotFoundException::new);
        setChildren(item);
        BeanUtils.copyProperties(item, itemFromDB, "id");
        return repository.save(itemFromDB);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * функция поиска в БД и восстановления дочерних сущностей в родительской по ID
     * @param item - сущность, требующая восстановления дочерних сущностей
     */
    private void setChildren(Order item) {
        item.setBaguettes(new HashSet<>(baguetteRepo
                .findAllById(item.getBaguettes().stream().map(Baguette::getId).collect(Collectors.toSet()))));
        item.setCutters(new HashSet<>(cutterRepo
                .findAllById(item.getCutters().stream().map(Cutter::getId).collect(Collectors.toSet()))));
        item.setTechnologicalProcesses(new HashSet<>(technologicalProcessRepo
                .findAllById(item.getTechnologicalProcesses().stream().map(TechnologicalProcess::getId).collect(Collectors.toSet()))));
    }

}
