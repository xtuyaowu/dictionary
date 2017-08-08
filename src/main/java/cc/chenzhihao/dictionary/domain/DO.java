package cc.chenzhihao.dictionary.domain;

import cc.chenzhihao.dictionary.service.PaService;

/**
 * Des : <dictionary> :
 *
 * @author chenzhihao
 * @version 创建时间: 2017/8/6 00:34
 */
public class DO implements Runnable {

    private PaService paService;

    public DO(PaService paService) {
        this.paService = paService;
    }

    @Override
    public void run() {
        Long size;
        while ((size = paService.listSize()) > 0) {
            Company company = paService.getCompany(paService.popUrl());
            paService.save(company);
            System.out.println("剩余：" + paService.listSize());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}