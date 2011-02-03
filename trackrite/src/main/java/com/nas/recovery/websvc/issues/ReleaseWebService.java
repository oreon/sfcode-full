package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.Release;
import java.util.List;

@WebService
public interface ReleaseWebService {

	public Release loadById(Long id);

	public List<Release> findByExample(Release exampleRelease);

	public void save(Release release);

}
